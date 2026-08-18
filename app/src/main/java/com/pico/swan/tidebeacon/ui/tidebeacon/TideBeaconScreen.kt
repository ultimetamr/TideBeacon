package com.pico.swan.tidebeacon.ui.tidebeacon

import android.os.SystemClock
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pico.spatial.core.ecs.Entity
import com.pico.spatial.core.ecs.ModelComponent
import com.pico.spatial.core.ecs.TransformComponent
import com.pico.spatial.core.ecs.resource.BlendingMode
import com.pico.spatial.core.ecs.resource.MeshResource
import com.pico.spatial.core.ecs.resource.UnlitMaterial
import com.pico.spatial.core.math.Color4
import com.pico.spatial.core.math.EulerAngles
import com.pico.spatial.core.math.Vector3
import com.pico.spatial.ui.foundation.content.SpatialView
import com.pico.spatial.ui.foundation.material.backgroundMaterial
import com.pico.spatial.ui.platform.Material
import com.pico.spatial.ui.platform.containers.LocalSpatialNavigator
import com.pico.swan.tidebeacon.data.repository.LocalPracticeRecordRepository
import com.pico.swan.tidebeacon.domain.model.PracticeScreen
import com.pico.swan.tidebeacon.domain.model.BreathPhase
import com.pico.swan.tidebeacon.domain.model.SceneChoice
import com.pico.swan.tidebeacon.domain.usecase.SavePracticeRecordUseCase
import com.pico.swan.tidebeacon.platform.SpatialBreathAudio
import com.pico.swan.tidebeacon.platform.LaunchActivity
import com.pico.swan.tidebeacon.ui.tidebeacon.components.CompletedPanel
import com.pico.swan.tidebeacon.ui.tidebeacon.components.IntroPanel
import com.pico.swan.tidebeacon.ui.tidebeacon.components.PracticePanel
import com.pico.swan.tidebeacon.ui.tidebeacon.components.SetupPanel
import com.pico.swan.tidebeacon.ui.tidebeacon.components.TutorialPanel
import kotlinx.coroutines.launch

@Composable
fun TideBeaconScreen() {
    val context = LocalContext.current
    val viewModel: TideBeaconViewModel = viewModel(
        factory = remember {
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T = TideBeaconViewModel(
                    SavePracticeRecordUseCase(LocalPracticeRecordRepository(context.applicationContext))
                ) as T
            }
        }
    )
    val state by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val navigator = LocalSpatialNavigator.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        when (LaunchActivity.previewScene) {
            "clouds" -> viewModel.onEvent(TideBeaconEvent.SelectScene(SceneChoice.CLOUDS))
            "dunes" -> viewModel.onEvent(TideBeaconEvent.SelectScene(SceneChoice.DUNES))
            "sea" -> viewModel.onEvent(TideBeaconEvent.SelectScene(SceneChoice.SEA))
        }
        when (LaunchActivity.previewScreen) {
            "tutorial" -> viewModel.onEvent(TideBeaconEvent.OpenTutorial)
            "setup" -> viewModel.onEvent(TideBeaconEvent.OpenSetup)
            "practice" -> {
                viewModel.onEvent(TideBeaconEvent.OpenSetup)
                viewModel.onEvent(TideBeaconEvent.Start(SystemClock.elapsedRealtime()))
            }
        }
        LaunchActivity.previewScreen = null
        LaunchActivity.previewScene = null
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_STOP -> viewModel.onEvent(TideBeaconEvent.AppPaused(SystemClock.elapsedRealtime()))
                Lifecycle.Event.ON_START -> viewModel.onEvent(TideBeaconEvent.AppResumed(SystemClock.elapsedRealtime()))
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    LaunchedEffect(state.screen, state.paused) {
        if (state.screen == PracticeScreen.PRACTICE && !state.paused) {
            while (true) {
                withFrameNanos { viewModel.onEvent(TideBeaconEvent.Tick(SystemClock.elapsedRealtime())) }
            }
        }
    }

    key(state.scene, state.screen) {
        TideBeaconStage(
            state = state,
            onEvent = viewModel::onEvent,
            onExit = { scope.launch { navigator.closeStage() } },
        )
    }
}

@Composable
private fun TideBeaconStage(
    state: TideBeaconUiState,
    onEvent: (TideBeaconEvent) -> Unit,
    onExit: () -> Unit,
) {
    var audio by remember { mutableStateOf<SpatialBreathAudio?>(null) }
    val beaconRings = remember { mutableListOf<BeaconGlowRing>() }
    val sceneEntities = remember { SceneEntityLifetime<Entity>(Entity::destroy) }
    val ringCue = remember { RingTransitionCue() }
    val nowMs = { SystemClock.elapsedRealtime() }

    LaunchedEffect(state.snapshot?.audioGain, state.paused, state.screen) {
        audio?.update(
            gain = state.snapshot?.audioGain ?: 0f,
            shouldPause = state.paused || state.screen != PracticeScreen.PRACTICE,
        )
    }
    DisposableEffect(Unit) {
        onDispose {
            audio?.release()
            beaconRings.clear()
            sceneEntities.destroyAll()
        }
    }

    SpatialView(
        modifier = Modifier.fillMaxSize(),
        initial = { content, attachments ->
            fun addEntity(entity: Entity): Entity = sceneEntities.track(entity).also(content::addEntity)

            attachments.entity(id = "state_panel")?.apply {
                val panelPosition = if (state.screen == PracticeScreen.PRACTICE) {
                    // 菜单入口放在视野右下方，不遮挡灯塔和风景。
                    Vector3(.72f, .72f, -1.8f)
                } else {
                    Vector3(0f, 1.15f, -1.8f)
                }
                components[TransformComponent::class.java]?.setPosition(panelPosition)
                addEntity(this)
            }

            val boxMesh = MeshResource.createBox(Vector3(1f, 1f, 1f), .04f)
            val sphereMesh = MeshResource.createSphere(.5f)
            val cylinderMesh = MeshResource.createCylinder(.5f, 1f)
            fun material(red: Float, green: Float, blue: Float) =
                UnlitMaterial.create(BlendingMode.OPAQUE).apply {
                    setBaseColor(Color4(red, green, blue, 1f))
                }
            val cloudMaterial = material(.78f, .86f, .92f)
            val duneMaterial = material(.73f, .48f, .24f)
            val lighthouseWhite = material(.86f, .84f, .76f)
            val lighthouseRed = material(.72f, .16f, .12f)
            val beaconLight = material(1f, .72f, .22f)

            fun primitive(
                mesh: MeshResource,
                surface: UnlitMaterial,
                position: Vector3,
                scale: Vector3,
                rotation: EulerAngles? = null,
            ): Entity = Entity().apply {
                    components.set(ModelComponent(mesh, surface))
                    components[TransformComponent::class.java]?.apply {
                        setPosition(position)
                        setScaleVector(scale)
                        rotation?.let(::setEulerAngles)
                    }
                    addEntity(this)
                }

            if (state.screen != PracticeScreen.INTRO) {
                when (state.scene) {
                    com.pico.swan.tidebeacon.domain.model.SceneChoice.SEA ->
                        addEntity(createLowPolySeaEntity())
                    com.pico.swan.tidebeacon.domain.model.SceneChoice.CLOUDS -> {
                        val cloudPositions = listOf(
                            Vector3(-2.6f, 2.6f, -4f),
                            Vector3(-.8f, 3.3f, -4.8f),
                            Vector3(.9f, 2.8f, -4.2f),
                            Vector3(2.6f, 3.6f, -5f),
                        )
                        cloudPositions.forEachIndexed { index, position ->
                            runCatching {
                                Entity.loadSuspend("asset://third_party/quaternius_cloud.glb").apply {
                                    components[TransformComponent::class.java]?.apply {
                                        setPosition(position)
                                        // The measured source is 2.97 m wide. These wrappers make
                                        // each cloud roughly 1.77-2.26 m wide in the enlarged scene.
                                        val scale = (.13f + index * .012f) * TIDE_BEACON_SCENERY_SCALE
                                        setScaleVector(Vector3(scale * 1.35f, scale * .72f, scale))
                                        setEulerAngles(EulerAngles(0f, index * 37f, 0f))
                                    }
                                    addEntity(this)
                                }
                            }.getOrElse {
                                primitive(
                                    sphereMesh,
                                    cloudMaterial,
                                    position,
                                    Vector3(
                                        .62f * TIDE_BEACON_SCENERY_SCALE,
                                        .22f * TIDE_BEACON_SCENERY_SCALE,
                                        .34f * TIDE_BEACON_SCENERY_SCALE,
                                    ),
                                )
                            }
                        }
                    }
                    com.pico.swan.tidebeacon.domain.model.SceneChoice.DUNES -> {
                        addEntity(createLowPolyDuneFieldEntity())
                        val importedDesert = runCatching {
                            Entity.loadSuspend("asset://third_party/poly_desert_scene.glb").apply {
                                // The source scene ships with a large rectangular ground plane.
                                // Remove only that mesh so the remaining dunes read as a diorama.
                                findEntity("Plane008")?.destroy()
                                components[TransformComponent::class.java]?.apply {
                                    // Keep the imported terrain as a distant diorama rather than
                                    // a rectangular carpet covering the user's physical room.
                                    setPosition(Vector3(1.8f, .5f, -3.2f))
                                    val scale = .0000003f * TIDE_BEACON_SCENERY_SCALE
                                    setScaleVector(Vector3(scale, scale, scale))
                                }
                                addEntity(this)
                            }
                        }.getOrNull()
                        if (importedDesert == null) {
                            repeat(6) { index ->
                                primitive(
                                    sphereMesh,
                                    duneMaterial,
                                    Vector3(
                                        (index - 2.5f) * .72f,
                                        .58f + (index % 3) * .09f,
                                        -2.8f,
                                    ),
                                    Vector3(
                                        .72f * TIDE_BEACON_SCENERY_SCALE,
                                        (.24f + (index % 3) * .08f) * TIDE_BEACON_SCENERY_SCALE,
                                        .54f * TIDE_BEACON_SCENERY_SCALE,
                                    ),
                                )
                            }
                        }
                    }
                }

            }

            // 首选完整的低多边形灯塔；加载失败时仍保留程序化占位，避免场景空白。
            val importedLighthouse = runCatching {
                Entity.loadSuspend("asset://third_party/poly_lighthouse.glb").apply {
                    components[TransformComponent::class.java]?.apply {
                        setPosition(Vector3(-1.1f, .5f, -2.8f))
                        val scale = .056f * TIDE_BEACON_SCENERY_SCALE
                        setScaleVector(Vector3(scale, scale, scale))
                    }
                    addEntity(this)
                }
            }.getOrNull()
            val audioAnchor = importedLighthouse ?: run {
                val upright = EulerAngles(90f, 0f, 0f)
                primitive(cylinderMesh, lighthouseRed, Vector3(-1.17f, 1.04f, -2.8f), Vector3(1.43f, 1.43f, .48f), upright)
                primitive(cylinderMesh, lighthouseWhite, Vector3(-1.17f, 2.1f, -2.8f), Vector3(.82f, .82f, 1.9f), upright)
                primitive(cylinderMesh, lighthouseRed, Vector3(-1.17f, 3.19f, -2.8f), Vector3(1.02f, 1.02f, .34f), upright)
                primitive(sphereMesh, beaconLight, Vector3(-1.17f, 3.63f, -2.8f), Vector3(.54f, .48f, .54f)).also {
                    primitive(sphereMesh, lighthouseRed, Vector3(-1.17f, 4.1f, -2.8f), Vector3(1.02f, .34f, 1.02f))
                }
            }
            beaconRings.clear()
            beaconRings += createBeaconGlowRingEntity(
                .25f * TIDE_BEACON_SCENERY_SCALE,
                .026f * TIDE_BEACON_SCENERY_SCALE,
            ).also { ring ->
                // 水平围绕灯塔灯室，保持原本的空间语义。
                ring.entity.components[TransformComponent::class.java]?.setPosition(Vector3(-1.17f, 3.56f, -2.6f))
                addEntity(ring.entity)
            }
            audio = runCatching { SpatialBreathAudio.attachTo(audioAnchor) }.getOrNull()
        },
        update = { _, attachments ->
            val haloScale = state.snapshot?.haloScale ?: .72f
            val snapshot = state.snapshot
            val now = SystemClock.elapsedRealtime()
            snapshot?.phase?.let { phase ->
                val direction = when (phase) {
                    BreathPhase.INHALE -> BreathDirection.IN
                    BreathPhase.EXHALE -> BreathDirection.OUT
                    BreathPhase.INHALE_HOLD, BreathPhase.EXHALE_HOLD -> null
                }
                if (direction != null) {
                    if (ringCue.lastDirection == null) {
                        ringCue.lastDirection = direction
                    } else if (direction != ringCue.lastDirection) {
                        ringCue.lastDirection = direction
                        ringCue.flashStartMs = now
                    }
                }
            }
            val flashElapsedMs = now - ringCue.flashStartMs
            val phaseFlash = if (flashElapsedMs in 0L..420L) {
                .28f * (1f - flashElapsedMs / 420f)
            } else {
                0f
            }
            val lightness = ((haloScale - .72f) / .48f).coerceIn(0f, 1f)
            val flashStrength = (phaseFlash / .28f).coerceIn(0f, 1f)
            val glowIntensity = maxOf(lightness, flashStrength)
            beaconRings.forEach { ring ->
                ring.entity.enabled = state.screen == PracticeScreen.PRACTICE
                ring.material.setBaseColor(incandescentGlowColor(glowIntensity))
                ring.entity.components[TransformComponent::class.java]?.apply {
                    val ringScale = .78f + haloScale * .36f
                    setScaleVector(Vector3(ringScale, ringScale, ringScale))
                }
            }
        },
        attachments = {
            AttachmentPanel(id = "state_panel") {
                val panelModifier = when (state.screen) {
                    PracticeScreen.INTRO -> Modifier.size(760.dp, 470.dp)
                    PracticeScreen.TUTORIAL -> Modifier
                        .size(760.dp, 560.dp)
                        .clip(RoundedCornerShape(28.dp))
                        .backgroundMaterial(true, Material.Regular)
                    PracticeScreen.PRACTICE -> Modifier
                        .size(310.dp, 86.dp)
                        .clip(RoundedCornerShape(28.dp))
                        .backgroundMaterial(true, Material.Regular)
                    PracticeScreen.COMPLETED -> Modifier
                        .size(520.dp, 260.dp)
                        .clip(RoundedCornerShape(28.dp))
                        .backgroundMaterial(true, Material.Regular)
                    PracticeScreen.SETUP -> Modifier
                        .size(960.dp, 660.dp)
                        .clip(RoundedCornerShape(28.dp))
                        .backgroundMaterial(true, Material.Regular)
                }
                Box(panelModifier, contentAlignment = Alignment.Center) {
                    when (state.screen) {
                        PracticeScreen.INTRO -> IntroPanel { onEvent(TideBeaconEvent.OpenTutorial) }
                        PracticeScreen.TUTORIAL -> TutorialPanel { onEvent(TideBeaconEvent.OpenSetup) }
                        PracticeScreen.SETUP -> SetupPanel(state, onEvent, nowMs)
                        PracticeScreen.PRACTICE -> PracticePanel(state, onEvent, nowMs, onExit)
                        PracticeScreen.COMPLETED -> CompletedPanel(
                            state = state,
                            onSave = { onEvent(TideBeaconEvent.SaveLocalRecord(System.currentTimeMillis())) },
                            onRestart = { onEvent(TideBeaconEvent.ReturnToSetup) },
                        )
                    }
                }
            }
        },
    )
}

private class RingTransitionCue {
    var lastDirection: BreathDirection? = null
    var flashStartMs: Long = Long.MIN_VALUE
}

private enum class BreathDirection { IN, OUT }
