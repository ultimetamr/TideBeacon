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
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/** A broad oval dune field with a tapered edge, used beneath imported desert details. */
internal fun createLowPolyDuneFieldEntity(): Entity {
    val positions = ArrayList<Vector3>()
    val normals = ArrayList<Vector3>()
    val colors = ArrayList<Color4>()
    val indices = ArrayList<Int>()
    val palette = listOf(
        Color4(.42f, .25f, .11f, 1f),
        Color4(.49f, .3f, .14f, 1f),
        Color4(.56f, .35f, .17f, 1f),
        Color4(.62f, .4f, .2f, 1f),
    )

    fun point(ring: Int, segment: Int): Vector3 {
        val radius = ring.toFloat() / DUNE_RINGS
        val angle = 2.0 * PI * segment / DUNE_SEGMENTS
        val ridge = (sin(angle * 2.0 + radius * 2.4) * .055).toFloat()
        val height = (1f - radius) * (.28f + ridge) * TIDE_BEACON_SCENERY_SCALE
        return Vector3(
            DUNE_CENTER_X + cos(angle).toFloat() * DUNE_RADIUS_X * radius,
            DUNE_BASE_Y + height,
            DUNE_CENTER_Z + sin(angle).toFloat() * DUNE_RADIUS_Z * radius,
        )
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

    repeat(DUNE_RINGS) { ringIndex ->
        val innerRing = ringIndex
        val outerRing = ringIndex + 1
        repeat(DUNE_SEGMENTS) { segment ->
            val next = (segment + 1) % DUNE_SEGMENTS
            val innerA = point(innerRing, segment)
            val innerB = point(innerRing, next)
            val outerA = point(outerRing, segment)
            val outerB = point(outerRing, next)
            val tone = palette[(ringIndex + segment) % palette.size]
            triangle(innerA, outerA, outerB, tone)
            if (innerRing > 0) triangle(innerA, outerB, innerB, tone)
        }
    }

    val mesh = MeshResource.createWithMeshModel(
        MeshModel(positions, indices, normals, emptyList(), emptyList(), emptyList(), colors),
        BoundingBox(
            Vector3(DUNE_CENTER_X, DUNE_BASE_Y + .14f, DUNE_CENTER_Z),
            Vector3(DUNE_RADIUS_X, .32f * TIDE_BEACON_SCENERY_SCALE, DUNE_RADIUS_Z),
        ),
        "TideBeaconDuneField",
    )
    val material = UnlitMaterial.create(BlendingMode.OPAQUE).apply {
        setBaseColor(Color4(1f, 1f, 1f, 1f))
        setCullingMode(MaterialCullingMode.NONE)
    }
    return Entity().apply { components.set(ModelComponent(mesh, material)) }
}

private const val DUNE_RINGS = 4
private const val DUNE_SEGMENTS = 18
private const val DUNE_CENTER_X = -.1f
private const val DUNE_CENTER_Z = -3f
private const val DUNE_BASE_Y = .5f
private const val DUNE_RADIUS_X = 1.55f * TIDE_BEACON_SCENERY_SCALE
private const val DUNE_RADIUS_Z = .78f * TIDE_BEACON_SCENERY_SCALE
