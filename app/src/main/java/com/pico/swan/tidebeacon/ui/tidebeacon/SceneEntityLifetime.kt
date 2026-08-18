package com.pico.swan.tidebeacon.ui.tidebeacon

/** Owns the entities created for one SpatialView lifetime. */
internal class SceneEntityLifetime<T>(
    private val destroy: (T) -> Unit,
) {
    private val entities = mutableListOf<T>()
    private var destroyed = false

    fun track(entity: T): T {
        check(!destroyed) { "Cannot track an entity after this scene lifetime is destroyed." }
        entities += entity
        return entity
    }

    fun destroyAll() {
        if (destroyed) return
        destroyed = true
        entities.asReversed().forEach(destroy)
        entities.clear()
    }
}
