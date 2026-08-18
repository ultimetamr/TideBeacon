package com.pico.swan.tidebeacon.platform

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import com.pico.spatial.audio.SpatialAudioTrackExtension
import com.pico.spatial.core.ecs.Entity
import kotlin.math.PI
import kotlin.math.sin

class SpatialBreathAudio private constructor(
    private val track: AudioTrack,
    private val extension: SpatialAudioTrackExtension,
) {
    private var paused = false

    fun update(gain: Float, shouldPause: Boolean) {
        if (shouldPause) {
            track.setVolume(0f)
            if (!paused) track.pause()
            paused = true
            return
        }
        if (paused) track.play()
        paused = false
        track.setVolume(gain.coerceIn(0f, 0.22f))
    }

    fun release() {
        track.setVolume(0f)
        track.stop()
        track.release()
        extension.release()
    }

    companion object {
        fun attachTo(lighthouse: Entity): SpatialBreathAudio {
            val sampleRate = 48_000
            val samples = ShortArray(sampleRate) { index ->
                (sin(2.0 * PI * 240.0 * index / sampleRate) * Short.MAX_VALUE * 0.16).toInt().toShort()
            }
            val builder = AudioTrack.Builder()
                .setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
                )
                .setAudioFormat(
                    AudioFormat.Builder()
                        .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                        .setSampleRate(sampleRate)
                        .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                        .build()
                )
                .setTransferMode(AudioTrack.MODE_STATIC)
                .setBufferSizeInBytes(samples.size * 2)
            val extension = SpatialAudioTrackExtension()
            extension.spatialAudioTrackExtensionConfig(
                SpatialAudioTrackExtension.SpatialAudioMode.OBJECT,
                false,
                builder,
            )
            extension.attachToEntityWithBuilder(lighthouse, builder)
            val track = builder.build()
            track.write(samples, 0, samples.size)
            track.setLoopPoints(0, samples.size, -1)
            track.setVolume(0f)
            track.play()
            return SpatialBreathAudio(track, extension)
        }
    }
}
