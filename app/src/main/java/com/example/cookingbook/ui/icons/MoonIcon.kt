package com.example.cookingbook.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val PhosphorMoon: ImageVector
    get() {
        if (_PhosphorMoon != null) return _PhosphorMoon!!

        _PhosphorMoon = ImageVector.Builder(
            name = "moon",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 256f,
            viewportHeight = 256f
        ).apply {
            path(
                fill = SolidColor(Color.Black)
            ) {
                moveTo(232.13f, 143.64f)
                arcToRelative(6f, 6f, 0f, false, false, -6f, -1.49f)
                arcTo(90.07f, 90.07f, 0f, false, true, 113.86f, 29.85f)
                arcToRelative(6f, 6f, 0f, false, false, -7.49f, -7.48f)
                arcTo(102.88f, 102.88f, 0f, false, false, 54.48f, 58.68f)
                arcTo(102f, 102f, 0f, false, false, 197.32f, 201.52f)
                arcToRelative(102.88f, 102.88f, 0f, false, false, 36.31f, -51.89f)
                arcTo(6f, 6f, 0f, false, false, 232.13f, 143.64f)
                close()
                moveToRelative(-42f, 48.29f)
                arcToRelative(90f, 90f, 0f, false, true, -126f, -126f)
                arcTo(90.9f, 90.9f, 0f, false, true, 99.65f, 37.66f)
                arcTo(102.06f, 102.06f, 0f, false, false, 218.34f, 156.35f)
                arcTo(90.9f, 90.9f, 0f, false, true, 190.1f, 191.93f)
                close()
            }
        }.build()

        return _PhosphorMoon!!
    }

private var _PhosphorMoon: ImageVector? = null