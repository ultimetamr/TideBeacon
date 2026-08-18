package com.pico.swan.tidebeacon.ui.tidebeacon.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pico.spatial.ui.design.PicoTheme
import com.pico.spatial.ui.foundation.hover.spatialHoverEffect
import com.pico.spatial.ui.foundation.haptic.controllerHapticFeedback

@Composable
fun BreathHalo(
    scale: Float,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    containerSize: Dp = 220.dp,
    ringSize: Dp = 180.dp,
    showCenter: Boolean = true,
) {
    val ring = PicoTheme.colorScheme.interaction
    val center = PicoTheme.colorScheme.fillSecondary
    val interactionSource = remember { MutableInteractionSource() }
    val interactive = if (onClick == null) Modifier else Modifier
        .clip(CircleShape)
        .spatialHoverEffect()
        .clickable(
            interactionSource = interactionSource,
            indication = LocalIndication.current,
            onClick = onClick,
        )
        .controllerHapticFeedback(interactionSource = interactionSource)

    Box(modifier = Modifier.size(containerSize).then(modifier).then(interactive), contentAlignment = Alignment.Center) {
        Canvas(
            Modifier
                .size(ringSize)
                .graphicsLayer { scaleX = scale; scaleY = scale }
        ) {
            if (showCenter) drawCircle(color = center, radius = size.minDimension * 0.39f)
            drawCircle(color = ring, radius = size.minDimension * 0.46f, style = androidx.compose.ui.graphics.drawscope.Stroke(width = 8.dp.toPx()))
        }
    }
}
