package com.example.composeyuva.login.ui.theme

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

fun RoundedTopShape(cornerRadius: Dp) = object : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        // Convert Dp to Px using density
        val radiusPx = with(density) { cornerRadius.toPx() }

        // Create the outline with rounded top corners
        val path = Path().apply {
            addRoundRect(
                RoundRect(
                    rect = Rect(0f, 0f, size.width, size.height),
                    topLeft = CornerRadius(radiusPx, radiusPx),
                    topRight = CornerRadius(radiusPx, radiusPx)
                )
            )
        }
        return Outline.Generic(path)
    }
}

