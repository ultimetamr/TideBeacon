package com.pico.swan.tidebeacon.ui.tidebeacon

import com.pico.spatial.core.ecs.BoundingBox
import com.pico.spatial.core.ecs.Entity
import com.pico.spatial.core.ecs.ModelComponent
import com.pico.spatial.core.ecs.resource.BlendingMode
import com.pico.spatial.core.ecs.resource.MaterialCullingMode
import com.pico.spatial.core.ecs.resource.MeshModel
import com.pico.spatial.core.ecs.resource.MeshResource
import com.pico.spatial.core.ecs.resource.UnlitMaterial
import com.pico.spatial.core.math.Color4
import com.pico.spatial.core.math.Vector3
import kotlin.math.sin

/** A broad, continuous faceted sea surrounding the enlarged lighthouse. */
internal fun createLowPolySeaEntity(): Entity {
    val positions = ArrayList<Vector3>(SEA_COLUMNS * SEA_ROWS * 12)
    val normals = ArrayList<Vector3>(SEA_COLUMNS * SEA_ROWS * 12)
    val colors = ArrayList<Color4>(SEA_COLUMNS * SEA_ROWS * 12)
    val indices = ArrayList<Int>(SEA_COLUMNS * SEA_ROWS * 12)
    val palette = listOf(
        Color4(.008f, .075f, .13f, 1f),
        Color4(.01f, .09f, .15f, 1f),
        Color4(.012f, .105f, .17f, 1f),
        Color4(.015f, .12f, .19f, 1f),
        Color4(.02f, .135f, .21f, 1f),
    )

    fun point(column: Int, row: Int): Vector3 {
        val x = SEA_MIN_X + (SEA_MAX_X - SEA_MIN_X) * column / SEA_COLUMNS
        val z = SEA_NEAR_Z + (SEA_FAR_Z - SEA_NEAR_Z) * row / SEA_ROWS
        val broadWave = sin(column * .71f + row * .43f) * .032f * TIDE_BEACON_SCENERY_SCALE
        val smallRipple = sin(column * 1.91f - row * 1.17f) * .012f * TIDE_BEACON_SCENERY_SCALE
        val wave = broadWave + smallRipple
        return Vector3(x, SEA_CENTER_Y + wave, z)
    }

    fun triangle(a: Vector3, b: Vector3, c: Vector3, color: Color4) {
        val base = positions.size
        positions += a
        positions += b
        positions += c
        repeat(3) {
            normals += Vector3(0f, 1f, 0f)
            colors += color
        }
        indices += base
        indices += base + 1
        indices += base + 2
    }

    repeat(SEA_ROWS) { row ->
        repeat(SEA_COLUMNS) { column ->
            val nearLeft = point(column, row)
            val nearRight = point(column + 1, row)
            val farLeft = point(column, row + 1)
            val farRight = point(column + 1, row + 1)
            val tone = (column * 3 + row * 2) % palette.size
            triangle(nearLeft, farLeft, nearRight, palette[tone])
            triangle(nearRight, farLeft, farRight, palette[(tone + 1) % palette.size])
        }
    }

    val model = MeshModel(
        positions,
        indices,
        normals,
        emptyList(),
        emptyList(),
        emptyList(),
        colors,
    )
    val bounds = BoundingBox(
        Vector3((SEA_MIN_X + SEA_MAX_X) / 2f, SEA_CENTER_Y, (SEA_NEAR_Z + SEA_FAR_Z) / 2f),
        Vector3(
            (SEA_MAX_X - SEA_MIN_X) / 2f,
            .08f * TIDE_BEACON_SCENERY_SCALE,
            (SEA_NEAR_Z - SEA_FAR_Z) / 2f,
        ),
    )
    val mesh = MeshResource.createWithMeshModel(model, bounds, "TideBeaconLowPolySea")
    val material = UnlitMaterial.create(BlendingMode.OPAQUE).apply {
        setBaseColor(Color4(1f, 1f, 1f, 1f))
        setCullingMode(MaterialCullingMode.NONE)
    }
    return Entity().apply { components.set(ModelComponent(mesh, material)) }
}

private const val SEA_COLUMNS = 18
private const val SEA_ROWS = 10
private const val SEA_MIN_X = -1.7f * TIDE_BEACON_SCENERY_SCALE
private const val SEA_MAX_X = 1.7f * TIDE_BEACON_SCENERY_SCALE
private const val SEA_CENTER_Y = .52f
private const val SEA_NEAR_Z = -1.05f
private const val SEA_FAR_Z = SEA_NEAR_Z - 1.43f * TIDE_BEACON_SCENERY_SCALE
