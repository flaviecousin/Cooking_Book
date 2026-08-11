package com.example.cookingbook.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val FeatherFilter: ImageVector
    get() {
        if (_FeatherFilter != null) return _FeatherFilter!!

        _FeatherFilter = ImageVector.Builder(
            name = "filter",
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
                moveTo(22f, 3f)
                lineTo(2f, 3f)
                lineTo(10f, 12.46f)
                lineTo(10f, 19f)
                lineTo(14f, 21f)
                lineTo(14f, 12.46f)
                lineTo(22f, 3f)
                close()
            }
        }.build()

        return _FeatherFilter!!
    }

private var _FeatherFilter: ImageVector? = null