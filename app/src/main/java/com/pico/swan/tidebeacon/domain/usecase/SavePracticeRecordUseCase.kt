package com.pico.swan.tidebeacon.domain.usecase

import com.pico.swan.tidebeacon.data.repository.PracticeRecordRepository
import com.pico.swan.tidebeacon.domain.model.PracticeRecord

class SavePracticeRecordUseCase(private val repository: PracticeRecordRepository) {
    fun execute(record: PracticeRecord): Int {
        repository.save(record)
        return repository.count()
    }
}
