package com.pico.swan.tidebeacon.domain.model

enum class PracticeScreen { INTRO, TUTORIAL, SETUP, PRACTICE, COMPLETED }

enum class SceneChoice(val label: String) {
    SEA("海面"), CLOUDS("云层"), DUNES("沙丘")
}

enum class BreathPhase(val label: String) {
    INHALE("吸气"), INHALE_HOLD("停留"), EXHALE("呼气"), EXHALE_HOLD("停留")
}

data class TimelineSnapshot(
    val elapsedMs: Long,
    val remainingMs: Long,
    val phase: BreathPhase,
    val phaseProgress: Float,
    val haloScale: Float,
    val audioGain: Float,
    val completedCycles: Int,
    val completed: Boolean,
)

data class PracticeRecord(val completedAtEpochMs: Long, val durationMinutes: Int, val scene: SceneChoice)
