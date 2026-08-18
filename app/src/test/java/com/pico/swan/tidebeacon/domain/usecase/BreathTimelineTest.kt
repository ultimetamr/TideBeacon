package com.pico.swan.tidebeacon.domain.usecase

import com.pico.swan.tidebeacon.domain.model.BreathPattern
import com.pico.swan.tidebeacon.domain.model.BreathPhase
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.math.roundToLong

class BreathTimelineTest {
    @Test
    fun `absolute timeline has the same phase at every refresh rate`() {
        val observations = listOf(60, 72, 90, 120).map { hz ->
            val timeline = BreathTimeline(BreathPattern.Gentle, 120_000)
            timeline.start(10_000)
            val target = 43_210L
            val frames = (target / (1000.0 / hz)).roundToLong()
            repeat(frames.toInt()) { frame -> timeline.snapshot(10_000 + (frame * 1000.0 / hz).roundToLong()) }
            timeline.snapshot(10_000 + target)
        }
        observations.forEach { snapshot ->
            assertEquals(43_210L, snapshot.elapsedMs)
            assertEquals(BreathPhase.EXHALE, snapshot.phase)
            assertEquals(observations.first().phaseProgress, snapshot.phaseProgress, 0.0001f)
            assertEquals(observations.first().haloScale, snapshot.haloScale, 0.0001f)
        }
    }

    @Test
    fun `two minute flow completes within one frame at common refresh rates`() {
        listOf(60, 72, 90, 120).forEach { hz ->
            val timeline = BreathTimeline(BreathPattern.Even, 120_000)
            timeline.start(0)
            val frameMs = 1000.0 / hz
            var frame = 0L
            while (!timeline.snapshot((frame * frameMs).roundToLong()).completed) frame++
            val completedAt = (frame * frameMs).roundToLong()
            assertTrue("$hz Hz completed late at $completedAt", completedAt in 120_000..(120_000 + frameMs.toLong() + 1))
        }
    }

    @Test
    fun `pause freezes animation audio and countdown then resume fades in`() {
        val timeline = BreathTimeline(BreathPattern.Gentle, 120_000)
        timeline.start(1_000)
        timeline.pause(6_000)
        val frozen = timeline.snapshot(6_000)
        assertEquals(frozen.copy(audioGain = 0f), timeline.snapshot(40_000))
        timeline.resume(50_000)
        val resumed = timeline.snapshot(50_000)
        assertEquals(frozen.elapsedMs, resumed.elapsedMs)
        assertEquals(0f, resumed.audioGain, 0.0001f)
        assertTrue(timeline.snapshot(50_400).audioGain > 0f)
        assertEquals(5_400L, timeline.snapshot(50_400).elapsedMs)
    }

    @Test
    fun `cycle counter changes only after a complete cycle`() {
        val timeline = BreathTimeline(BreathPattern.Gentle, 120_000)
        timeline.start(0)
        assertEquals(0, timeline.snapshot(11_999).completedCycles)
        assertEquals(1, timeline.snapshot(12_000).completedCycles)
    }
}
