package com.pico.swan.tidebeacon.ui.tidebeacon.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pico.spatial.ui.design.Button
import com.pico.spatial.ui.design.PicoTheme
import com.pico.spatial.ui.design.Text
import com.pico.spatial.ui.foundation.material.backgroundMaterial
import com.pico.spatial.ui.platform.Material
import com.pico.swan.tidebeacon.domain.model.BreathPattern
import com.pico.swan.tidebeacon.domain.model.SceneChoice
import com.pico.swan.tidebeacon.ui.tidebeacon.TideBeaconEvent
import com.pico.swan.tidebeacon.ui.tidebeacon.TideBeaconUiState
import java.util.Locale

@Composable
fun IntroPanel(onOpenTutorial: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        BreathHalo(scale = .86f, onClick = onOpenTutorial)
        Text(
            text = "光扩张时吸气，光收回时呼气。",
            color = PicoTheme.colorScheme.labelPrimary,
            style = PicoTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun SetupPanel(state: TideBeaconUiState, onEvent: (TideBeaconEvent) -> Unit, nowMs: () -> Long) {
    Column(Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("选择一次练习", color = PicoTheme.colorScheme.labelPrimary, style = PicoTheme.typography.titleLarge)
        ChoiceRow("时长", listOf(2, 4, 6), state.durationMinutes, { "$it 分钟" }) { onEvent(TideBeaconEvent.SelectDuration(it)) }
        ChoiceRow("场景", SceneChoice.entries, state.scene, { it.label }) { onEvent(TideBeaconEvent.SelectScene(it)) }
        ChoiceRow("节奏", BreathPattern.choices, state.pattern, { it.label }) { onEvent(TideBeaconEvent.SelectPattern(it)) }
        Spacer(Modifier.height(4.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = { onEvent(TideBeaconEvent.OpenTutorial) }) { Text("观看教程") }
            Button(onClick = { onEvent(TideBeaconEvent.Start(nowMs())) }) { Text("开始") }
        }
    }
}

@Composable
fun TutorialPanel(onSkip: () -> Unit) {
    var page by remember { mutableIntStateOf(0) }
    val pages = listOf(
        "跟随灯塔光环" to "光环向外扩张时吸气，向内收回时呼气。",
        "注意一次亮光" to "呼气转吸气、吸气转呼气时，光环只会短暂亮一次。停留阶段不会闪。",
        "随时可以控制" to "使用右下角按钮暂停或继续；打开菜单可调整节奏，完成后可再次练习。",
    )
    val (title, body) = pages[page]
    Column(
        Modifier.padding(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Text("${page + 1} / ${pages.size}", color = PicoTheme.colorScheme.labelSecondary, style = PicoTheme.typography.bodyMedium)
        BreathHalo(scale = when (page) { 0 -> 1.12f; 1 -> .88f; else -> .74f })
        Text(title, color = PicoTheme.colorScheme.labelPrimary, style = PicoTheme.typography.titleLarge)
        Text(body, color = PicoTheme.colorScheme.labelSecondary, style = PicoTheme.typography.bodyLarge, textAlign = TextAlign.Center)
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            if (page > 0) Button(onClick = { page-- }) { Text("上一步") }
            Button(onClick = onSkip) { Text("跳过") }
            Button(onClick = { if (page == pages.lastIndex) onSkip() else page++ }) {
                Text(if (page == pages.lastIndex) "选择练习" else "下一步")
            }
        }
    }
}

@Composable
private fun <T> ChoiceRow(title: String, values: List<T>, selected: T, label: (T) -> String, select: (T) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(title, color = PicoTheme.colorScheme.labelSecondary, style = PicoTheme.typography.bodyMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            values.forEach { value ->
                Button(onClick = { select(value) }) { Text(if (value == selected) "● ${label(value)}" else label(value)) }
            }
        }
    }
}

@Composable
fun PracticePanel(state: TideBeaconUiState, onEvent: (TideBeaconEvent) -> Unit, nowMs: () -> Long, onExit: () -> Unit) {
    val snapshot = state.snapshot ?: return
    val totalSeconds = ((snapshot.remainingMs + 999) / 1000).toInt()
    val timer = String.format(Locale.US, "%02d:%02d", totalSeconds / 60, totalSeconds % 60)
    Row(
        Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(timer, color = PicoTheme.colorScheme.labelSecondary, style = PicoTheme.typography.bodyMedium)
        Spacer(Modifier.size(8.dp))
        Button(onClick = { onEvent(TideBeaconEvent.TogglePause(nowMs())) }) { Text(if (state.paused) "继续" else "暂停") }
        Spacer(Modifier.size(8.dp))
        Button(onClick = { onEvent(TideBeaconEvent.ReturnToSetup) }) { Text("菜单") }
    }
}

@Composable
fun CompletedPanel(state: TideBeaconUiState, onSave: () -> Unit, onRestart: () -> Unit) {
    Column(Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("完成一次练习", color = PicoTheme.colorScheme.labelPrimary, style = PicoTheme.typography.titleLarge)
        Button(onClick = onSave, enabled = !state.recordSaved) { Text(if (state.recordSaved) "已记录在本机 · ${state.localRecordCount}" else "记录在本机（可选）") }
        Button(onClick = onRestart) { Text("再练一次") }
    }
}
