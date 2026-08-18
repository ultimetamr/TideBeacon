package com.pico.swan.tidebeacon.domain.model

data class BreathPattern(
    val id: String,
    val label: String,
    val inhaleMs: Long,
    val inhaleHoldMs: Long,
    val exhaleMs: Long,
    val exhaleHoldMs: Long,
) {
    val cycleMs: Long = inhaleMs + inhaleHoldMs + exhaleMs + exhaleHoldMs

    companion object {
        val Gentle = BreathPattern("gentle", "舒缓 4·1·6·1", 4_000, 1_000, 6_000, 1_000)
        val Even = BreathPattern("even", "均匀 4·1·4·1", 4_000, 1_000, 4_000, 1_000)
        val Slow = BreathPattern("slow", "悠长 5·1·7·1", 5_000, 1_000, 7_000, 1_000)
        val choices = listOf(Gentle, Even, Slow)
    }
}
