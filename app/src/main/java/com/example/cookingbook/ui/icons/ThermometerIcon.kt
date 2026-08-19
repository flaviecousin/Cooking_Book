package com.example.cookingbook.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val FeatherThermometer: ImageVector
    get() {
        if (_FeatherThermometer != null) return _FeatherThermometer!!

        _FeatherThermometer = ImageVector.Builder(
            name = "thermometer",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color.Transparent),
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(14f, 14.76f)
                verticalLineTo(3.5f)
                arcToRelative(2.5f, 2.5f, 0f, false, false, -5f, 0f)
                verticalLineToRelative(11.26f)
                arcToRelative(4.5f, 4.5f, 0f, true, false, 5f, 0f)
                close()
            }
        }.build()

        return _FeatherThermometer!!
    }

private var _FeatherThermometer: ImageVector? = null