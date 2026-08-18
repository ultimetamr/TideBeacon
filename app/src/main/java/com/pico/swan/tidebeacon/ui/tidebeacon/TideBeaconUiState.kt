package com.pico.swan.tidebeacon.ui.tidebeacon

import com.pico.swan.tidebeacon.domain.model.BreathPattern
import com.pico.swan.tidebeacon.domain.model.PracticeScreen
import com.pico.swan.tidebeacon.domain.model.SceneChoice
import com.pico.swan.tidebeacon.domain.model.TimelineSnapshot

data class TideBeaconUiState(
    val screen: PracticeScreen = PracticeScreen.INTRO,
    val durationMinutes: Int = 2,
    val scene: SceneChoice = SceneChoice.SEA,
    val pattern: BreathPattern = BreathPattern.Gentle,
    val paused: Boolean = false,
    val pausedBySystem: Boolean = false,
    val snapshot: TimelineSnapshot? = null,
    val localRecordCount: Int = 0,
    val recordSaved: Boolean = false,
)

sealed interface TideBeaconEvent {
    data object OpenSetup : TideBeaconEvent
    data object OpenTutorial : TideBeaconEvent
    data class SelectDuration(val minutes: Int) : TideBeaconEvent
    data class SelectScene(val scene: SceneChoice) : TideBeaconEvent
    data class SelectPattern(val pattern: BreathPattern) : TideBeaconEvent
    data class Start(val nowMs: Long) : TideBeaconEvent
    data class Tick(val nowMs: Long) : TideBeaconEvent
    data class TogglePause(val nowMs: Long) : TideBeaconEvent
    data class Restart(val nowMs: Long) : TideBeaconEvent
    data class AppPaused(val nowMs: Long) : TideBeaconEvent
    data class AppResumed(val nowMs: Long) : TideBeaconEvent
    data object ReturnToSetup : TideBeaconEvent
    data class SaveLocalRecord(val epochMs: Long) : TideBeaconEvent
}
