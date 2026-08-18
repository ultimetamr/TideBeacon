package com.pico.swan.tidebeacon.domain.usecase

import com.pico.swan.tidebeacon.domain.model.BreathPattern
import com.pico.swan.tidebeacon.domain.model.BreathPhase
import com.pico.swan.tidebeacon.domain.model.TimelineSnapshot
import kotlin.math.ceil
import kotlin.math.min

class BreathTimeline(
    private val pattern: BreathPattern,
    private val durationMs: Long,
    private val resumeFadeMs: Long = 400,
) {
    private var originMs: Long? = null
    private var frozenElapsedMs = 0L
    private var paused = false
    private var fadeOriginMs: Long? = null

    fun start(nowMs: Long) {
        originMs = nowMs
        frozenElapsedMs = 0
        paused = false
        fadeOriginMs = nowMs
    }

    fun pause(nowMs: Long) {
        if (paused || originMs == null) return
        frozenElapsedMs = elapsedAt(nowMs)
        paused = true
    }

    fun resume(nowMs: Long) {
        if (!paused) return
        originMs = nowMs - frozenElapsedMs
        fadeOriginMs = nowMs
        paused = false
    }

    fun restart(nowMs: Long) = start(nowMs)

    fun snapshot(nowMs: Long): TimelineSnapshot {
        val elapsed = elapsedAt(nowMs).coerceIn(0, durationMs)
        val cyclePosition = if (pattern.cycleMs == 0L) 0L else elapsed % pattern.cycleMs
        val phaseData = phaseAt(cyclePosition)
        val scale = when (phaseData.first) {
            BreathPhase.INHALE -> 0.72f + 0.28f * phaseData.second
            BreathPhase.INHALE_HOLD -> 1f
            BreathPhase.EXHALE -> 1f - 0.28f * phaseData.second
            BreathPhase.EXHALE_HOLD -> 0.72f
        }
        val phaseGain = when (phaseData.first) {
            BreathPhase.INHALE -> 0.08f + 0.10f * phaseData.second
            BreathPhase.INHALE_HOLD -> 0.18f
            BreathPhase.EXHALE -> 0.18f - 0.10f * phaseData.second
            BreathPhase.EXHALE_HOLD -> 0.08f
        }
        val fade = if (paused) 0f else fadeOriginMs?.let { min(1f, (nowMs - it).coerceAtLeast(0).toFloat() / resumeFadeMs) } ?: 1f
        return TimelineSnapshot(
            elapsedMs = elapsed,
            remainingMs = (durationMs - elapsed).coerceAtLeast(0),
            phase = phaseData.first,
            phaseProgress = phaseData.second,
            haloScale = scale,
            audioGain = phaseGain * fade,
            completedCycles = (elapsed / pattern.cycleMs).toInt(),
            completed = elapsed >= durationMs,
        )
    }

    fun remainingSeconds(nowMs: Long): Int = ceil(snapshot(nowMs).remainingMs / 1000.0).toInt()

    private fun elapsedAt(nowMs: Long): Long = if (paused) frozenElapsedMs else originMs?.let { (nowMs - it).coerceAtLeast(0) } ?: 0

    private fun phaseAt(positionMs: Long): Pair<BreathPhase, Float> {
        var cursor = positionMs
        if (cursor < pattern.inhaleMs) return BreathPhase.INHALE to ratio(cursor, pattern.inhaleMs)
        cursor -= pattern.inhaleMs
        if (cursor < pattern.inhaleHoldMs) return BreathPhase.INHALE_HOLD to ratio(cursor, pattern.inhaleHoldMs)
        cursor -= pattern.inhaleHoldMs
        if (cursor < pattern.exhaleMs) return BreathPhase.EXHALE to ratio(cursor, pattern.exhaleMs)
        cursor -= pattern.exhaleMs
        return BreathPhase.EXHALE_HOLD to ratio(cursor, pattern.exhaleHoldMs)
    }

    private fun ratio(value: Long, duration: Long): Float = if (duration <= 0) 1f else (value.toFloat() / duration).coerceIn(0f, 1f)
}
