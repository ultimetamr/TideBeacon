package com.pico.swan.tidebeacon.ui.tidebeacon

import com.pico.spatial.core.ecs.BoundingBox
import com.pico.spatial.core.ecs.Entity
import com.pico.spatial.core.ecs.ModelComponent
import com.pico.spatial.core.ecs.resource.BlendingMode
import com.pico.spatial.core.ecs.resource.MaterialCullingMode
import com.pico.spatial.core.ecs.resource.MeshModel
import com.pico.spatial.core.ecs.resource.MeshResource
import com.pico.spatial.core.ecs.resource.UnlitMaterial
import com.pico.spatial.core.math.Color4
import com.pico.spatial.core.math.Vector3
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

internal data class BeaconGlowRing(val entity: Entity, val material: UnlitMaterial)

/** Near-white incandescent light with only a subtle warm shift across intensity. */
internal fun incandescentGlowColor(intensity: Float): Color4 {
    val amount = intensity.coerceIn(0f, 1f)
    return Color4(
        1f,
        INCANDESCENT_BASE_GREEN + (INCANDESCENT_PEAK_GREEN - INCANDESCENT_BASE_GREEN) * amount,
        INCANDESCENT_BASE_BLUE + (INCANDESCENT_PEAK_BLUE - INCANDESCENT_BASE_BLUE) * amount,
        1f,
    )
}

/** A real tube-shaped torus mesh, generated locally to avoid emulator primitive artifacts. */
internal fun createBeaconGlowRingEntity(majorRadius: Float, tubeRadius: Float): BeaconGlowRing {
    val positions = ArrayList<Vector3>(MAJOR_SEGMENTS * MINOR_SEGMENTS)
    val normals = ArrayList<Vector3>(MAJOR_SEGMENTS * MINOR_SEGMENTS)
    val colors = ArrayList<Color4>(MAJOR_SEGMENTS * MINOR_SEGMENTS)
    val indices = ArrayList<Int>(MAJOR_SEGMENTS * MINOR_SEGMENTS * 6)
    val initialColor = incandescentGlowColor(0f)

    repeat(MAJOR_SEGMENTS) { major ->
        val u = 2.0 * PI * major / MAJOR_SEGMENTS
        repeat(MINOR_SEGMENTS) { minor ->
            val v = 2.0 * PI * minor / MINOR_SEGMENTS
            val radial = majorRadius + tubeRadius * cos(v).toFloat()
            val nx = cos(u).toFloat() * cos(v).toFloat()
            val ny = sin(v).toFloat()
            val nz = sin(u).toFloat() * cos(v).toFloat()
            positions += Vector3(cos(u).toFloat() * radial, tubeRadius * sin(v).toFloat(), sin(u).toFloat() * radial)
            normals += Vector3(nx, ny, nz)
            colors += initialColor
        }
    }
    repeat(MAJOR_SEGMENTS) { major ->
        repeat(MINOR_SEGMENTS) { minor ->
            val nextMajor = (major + 1) % MAJOR_SEGMENTS
            val nextMinor = (minor + 1) % MINOR_SEGMENTS
            val a = major * MINOR_SEGMENTS + minor
            val b = nextMajor * MINOR_SEGMENTS + minor
            val c = major * MINOR_SEGMENTS + nextMinor
            val d = nextMajor * MINOR_SEGMENTS + nextMinor
            indices += listOf(a, b, c, c, b, d)
        }
    }

    val extent = majorRadius + tubeRadius
    val mesh = MeshResource.createWithMeshModel(
        MeshModel(positions, indices, normals, emptyList(), emptyList(), emptyList(), colors),
        BoundingBox(Vector3.ZERO, Vector3(extent, tubeRadius, extent)),
        "TideBeaconTubeGlowRing",
    )
    val material = UnlitMaterial.create(BlendingMode.OPAQUE).apply {
        setBaseColor(initialColor)
        setCullingMode(MaterialCullingMode.NONE)
    }
    return BeaconGlowRing(Entity().apply { components.set(ModelComponent(mesh, material)) }, material)
}

private const val MAJOR_SEGMENTS = 72
private const val MINOR_SEGMENTS = 12
private const val INCANDESCENT_BASE_GREEN = 244f / 255f
private const val INCANDESCENT_BASE_BLUE = 234f / 255f
private const val INCANDESCENT_PEAK_GREEN = 253f / 255f
private const val INCANDESCENT_PEAK_BLUE = 252f / 255f
