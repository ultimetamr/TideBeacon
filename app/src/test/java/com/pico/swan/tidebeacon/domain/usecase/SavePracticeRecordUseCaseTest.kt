package com.pico.swan.tidebeacon.domain.usecase

import com.pico.swan.tidebeacon.data.repository.PracticeRecordRepository
import com.pico.swan.tidebeacon.domain.model.PracticeRecord
import com.pico.swan.tidebeacon.domain.model.SceneChoice
import org.junit.Assert.assertEquals
import org.junit.Test

class SavePracticeRecordUseCaseTest {
    private class FakeRepository : PracticeRecordRepository {
        val records = mutableListOf<PracticeRecord>()
        override fun save(record: PracticeRecord) { records += record }
        override fun count(): Int = records.size
    }

    @Test
    fun `save returns updated local count`() {
        val repository = FakeRepository()
        val useCase = SavePracticeRecordUseCase(repository)
        assertEquals(1, useCase.execute(PracticeRecord(1, 2, SceneChoice.SEA)))
    }

    @Test
    fun `save preserves selected duration and scene`() {
        val repository = FakeRepository()
        val record = PracticeRecord(42, 6, SceneChoice.DUNES)
        SavePracticeRecordUseCase(repository).execute(record)
        assertEquals(record, repository.records.single())
    }
}
