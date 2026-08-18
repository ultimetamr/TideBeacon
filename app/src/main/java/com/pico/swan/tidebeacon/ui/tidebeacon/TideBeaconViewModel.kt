package com.pico.swan.tidebeacon.ui.tidebeacon

import androidx.lifecycle.ViewModel
import com.pico.swan.tidebeacon.domain.model.PracticeRecord
import com.pico.swan.tidebeacon.domain.model.PracticeScreen
import com.pico.swan.tidebeacon.domain.usecase.BreathTimeline
import com.pico.swan.tidebeacon.domain.usecase.SavePracticeRecordUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TideBeaconViewModel(
    private val saveRecord: SavePracticeRecordUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(TideBeaconUiState(localRecordCount = 0))
    val state: StateFlow<TideBeaconUiState> = _state.asStateFlow()
    private var timeline: BreathTimeline? = null

    fun onEvent(event: TideBeaconEvent) {
        when (event) {
            TideBeaconEvent.OpenSetup -> _state.update { it.copy(screen = PracticeScreen.SETUP) }
            TideBeaconEvent.OpenTutorial -> _state.update { it.copy(screen = PracticeScreen.TUTORIAL) }
            is TideBeaconEvent.SelectDuration -> _state.update { it.copy(durationMinutes = event.minutes) }
            is TideBeaconEvent.SelectScene -> _state.update { it.copy(scene = event.scene) }
            is TideBeaconEvent.SelectPattern -> _state.update { it.copy(pattern = event.pattern) }
            is TideBeaconEvent.Start -> start(event.nowMs)
            is TideBeaconEvent.Tick -> tick(event.nowMs)
            is TideBeaconEvent.TogglePause -> togglePause(event.nowMs)
            is TideBeaconEvent.Restart -> restart(event.nowMs)
            is TideBeaconEvent.AppPaused -> pauseFromSystem(event.nowMs)
            is TideBeaconEvent.AppResumed -> resumeFromSystem(event.nowMs)
            TideBeaconEvent.ReturnToSetup -> {
                timeline = null
                _state.update { it.copy(screen = PracticeScreen.SETUP, paused = false, pausedBySystem = false, snapshot = null) }
            }
            is TideBeaconEvent.SaveLocalRecord -> save(event.epochMs)
        }
    }

    private fun start(nowMs: Long) {
        val current = _state.value
        timeline = BreathTimeline(current.pattern, current.durationMinutes * 60_000L).also { it.start(nowMs) }
        _state.update { it.copy(screen = PracticeScreen.PRACTICE, paused = false, pausedBySystem = false, snapshot = timeline?.snapshot(nowMs), recordSaved = false) }
    }

    private fun tick(nowMs: Long) {
        val currentTimeline = timeline ?: return
        val snapshot = currentTimeline.snapshot(nowMs)
        _state.update {
            if (snapshot.completed) it.copy(screen = PracticeScreen.COMPLETED, paused = false, snapshot = snapshot)
            else it.copy(snapshot = snapshot)
        }
    }

    private fun togglePause(nowMs: Long) {
        val currentTimeline = timeline ?: return
        if (_state.value.paused) currentTimeline.resume(nowMs) else currentTimeline.pause(nowMs)
        _state.update { it.copy(paused = !it.paused, pausedBySystem = false, snapshot = currentTimeline.snapshot(nowMs)) }
    }

    private fun restart(nowMs: Long) {
        val currentTimeline = timeline ?: return start(nowMs)
        currentTimeline.restart(nowMs)
        _state.update { it.copy(screen = PracticeScreen.PRACTICE, paused = false, pausedBySystem = false, snapshot = currentTimeline.snapshot(nowMs), recordSaved = false) }
    }

    private fun pauseFromSystem(nowMs: Long) {
        if (_state.value.screen != PracticeScreen.PRACTICE || _state.value.paused) return
        timeline?.pause(nowMs)
        _state.update { it.copy(paused = true, pausedBySystem = true, snapshot = timeline?.snapshot(nowMs)) }
    }

    private fun resumeFromSystem(nowMs: Long) {
        if (!_state.value.pausedBySystem) return
        timeline?.resume(nowMs)
        _state.update { it.copy(paused = false, pausedBySystem = false, snapshot = timeline?.snapshot(nowMs)) }
    }

    private fun save(epochMs: Long) {
        if (_state.value.screen != PracticeScreen.COMPLETED || _state.value.recordSaved) return
        val current = _state.value
        val count = saveRecord.execute(PracticeRecord(epochMs, current.durationMinutes, current.scene))
        _state.update { it.copy(localRecordCount = count, recordSaved = true) }
    }
}
