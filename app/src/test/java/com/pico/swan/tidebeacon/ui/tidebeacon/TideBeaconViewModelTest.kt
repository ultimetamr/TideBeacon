package com.pico.swan.tidebeacon.ui.tidebeacon

import com.pico.swan.tidebeacon.data.repository.PracticeRecordRepository
import com.pico.swan.tidebeacon.domain.model.PracticeRecord
import com.pico.swan.tidebeacon.domain.model.PracticeScreen
import com.pico.swan.tidebeacon.domain.model.SceneChoice
import com.pico.swan.tidebeacon.domain.usecase.SavePracticeRecordUseCase
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class TideBeaconViewModelTest {
    private class FakeRepository : PracticeRecordRepository {
        val records = mutableListOf<PracticeRecord>()
        override fun save(record: PracticeRecord) { records += record }
        override fun count() = records.size
    }

    private fun viewModel(repository: FakeRepository = FakeRepository()) = TideBeaconViewModel(SavePracticeRecordUseCase(repository))

    @Test
    fun `initial state is sparse intro`() {
        assertEquals(PracticeScreen.INTRO, viewModel().state.value.screen)
    }

    @Test
    fun `selection starts a two minute practice`() {
        val vm = viewModel()
        vm.onEvent(TideBeaconEvent.OpenSetup)
        vm.onEvent(TideBeaconEvent.SelectScene(SceneChoice.CLOUDS))
        vm.onEvent(TideBeaconEvent.Start(1_000))
        assertEquals(PracticeScreen.PRACTICE, vm.state.value.screen)
        assertEquals(SceneChoice.CLOUDS, vm.state.value.scene)
        assertEquals(120_000L, vm.state.value.snapshot?.remainingMs)
    }

    @Test
    fun `system pause and resume preserve elapsed time`() {
        val vm = viewModel()
        vm.onEvent(TideBeaconEvent.Start(1_000))
        vm.onEvent(TideBeaconEvent.Tick(6_000))
        vm.onEvent(TideBeaconEvent.AppPaused(6_000))
        vm.onEvent(TideBeaconEvent.Tick(40_000))
        assertEquals(5_000L, vm.state.value.snapshot?.elapsedMs)
        assertTrue(vm.state.value.pausedBySystem)
        vm.onEvent(TideBeaconEvent.AppResumed(50_000))
        vm.onEvent(TideBeaconEvent.Tick(50_400))
        assertEquals(5_400L, vm.state.value.snapshot?.elapsedMs)
        assertFalse(vm.state.value.paused)
    }

    @Test
    fun `two minute tick reaches quiet completion`() {
        val vm = viewModel()
        vm.onEvent(TideBeaconEvent.Start(0))
        vm.onEvent(TideBeaconEvent.Tick(120_000))
        assertEquals(PracticeScreen.COMPLETED, vm.state.value.screen)
    }

    @Test
    fun `optional local record is saved once`() {
        val repository = FakeRepository()
        val vm = viewModel(repository)
        vm.onEvent(TideBeaconEvent.Start(0))
        vm.onEvent(TideBeaconEvent.Tick(120_000))
        vm.onEvent(TideBeaconEvent.SaveLocalRecord(123))
        vm.onEvent(TideBeaconEvent.SaveLocalRecord(124))
        assertEquals(1, repository.records.size)
        assertTrue(vm.state.value.recordSaved)
    }
}
