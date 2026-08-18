package com.pico.swan.tidebeacon.ui.tidebeacon

import org.junit.Assert.assertEquals
import org.junit.Test

class SceneEntityLifetimeTest {
    @Test
    fun `switching scene destroys every entity from the previous scene`() {
        val liveEntities = linkedSetOf("sea", "lighthouse", "halo")
        val seaLifetime = SceneEntityLifetime<String>(liveEntities::remove)
        liveEntities.forEach(seaLifetime::track)

        seaLifetime.destroyAll()
        liveEntities += listOf("clouds", "lighthouse-2", "halo-2")

        assertEquals(
            setOf("clouds", "lighthouse-2", "halo-2"),
            liveEntities,
        )
    }
}
