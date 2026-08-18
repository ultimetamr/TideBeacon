package com.pico.swan.tidebeacon.data.repository

import android.content.Context
import com.pico.swan.tidebeacon.domain.model.PracticeRecord
import com.pico.swan.tidebeacon.domain.model.SceneChoice

interface PracticeRecordRepository {
    fun save(record: PracticeRecord)
    fun count(): Int
}

class LocalPracticeRecordRepository(context: Context) : PracticeRecordRepository {
    private val preferences = context.getSharedPreferences("tide_beacon_records", Context.MODE_PRIVATE)

    override fun save(record: PracticeRecord) {
        val next = count() + 1
        preferences.edit()
            .putInt("count", next)
            .putLong("last_completed_at", record.completedAtEpochMs)
            .putInt("last_duration", record.durationMinutes)
            .putString("last_scene", record.scene.name)
            .apply()
    }

    override fun count(): Int = preferences.getInt("count", 0)
}
