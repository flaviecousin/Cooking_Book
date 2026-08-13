package com.example.cookingbook.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val HeroiconsPlus: ImageVector
    get() {
        if (_HeroiconsPlus != null) return _HeroiconsPlus!!

        _HeroiconsPlus = ImageVector.Builder(
            name = "plus",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color.Transparent),
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 1.5f,
                strokeLineJoin = StrokeJoin.Miter
            ) {
                moveTo(12f, 4.5f)
                verticalLineToRelative(15f)
                moveToRelative(7.5f, -7.5f)
                horizontalLineToRelative(-15f)
            }
        }.build()

        return _HeroiconsPlus!!
    }

private var _HeroiconsPlus: ImageVector? = null