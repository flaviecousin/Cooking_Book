package com.example.cookingbook.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val FluentuiSystemIconsAddCircle: ImageVector
    get() {
        if (_FluentuiSystemIconsAddCircle != null) return _FluentuiSystemIconsAddCircle!!

        _FluentuiSystemIconsAddCircle = ImageVector.Builder(
            name = "add-circle",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color.Black)
            ) {
                moveTo(12f, 2f)
                curveTo(17.5228f, 2f, 22f, 6.47715f, 22f, 12f)
                curveTo(22f, 17.5228f, 17.5228f, 22f, 12f, 22f)
                curveTo(6.47715f, 22f, 2f, 17.5228f, 2f, 12f)
                curveTo(2f, 6.47715f, 6.47715f, 2f, 12f, 2f)
                close()
                moveTo(12f, 3.5f)
                curveTo(7.30558f, 3.5f, 3.5f, 7.30558f, 3.5f, 12f)
                curveTo(3.5f, 16.6944f, 7.30558f, 20.5f, 12f, 20.5f)
                curveTo(16.6944f, 20.5f, 20.5f, 16.6944f, 20.5f, 12f)
                curveTo(20.5f, 7.30558f, 16.6944f, 3.5f, 12f, 3.5f)
                close()
                moveTo(12f, 7f)
                curveTo(12.4142f, 7f, 12.75f, 7.33579f, 12.75f, 7.75f)
                verticalLineTo(11.25f)
                horizontalLineTo(16.25f)
                curveTo(16.6642f, 11.25f, 17f, 11.5858f, 17f, 12f)
                curveTo(17f, 12.4142f, 16.6642f, 12.75f, 16.25f, 12.75f)
                horizontalLineTo(12.75f)
                verticalLineTo(16.25f)
                curveTo(12.75f, 16.6642f, 12.4142f, 17f, 12f, 17f)
                curveTo(11.5858f, 17f, 11.25f, 16.6642f, 11.25f, 16.25f)
                verticalLineTo(12.75f)
                horizontalLineTo(7.75f)
                curveTo(7.33579f, 12.75f, 7f, 12.4142f, 7f, 12f)
                curveTo(7f, 11.5858f, 7.33579f, 11.25f, 7.75f, 11.25f)
                horizontalLineTo(11.25f)
                verticalLineTo(7.75f)
                curveTo(11.25f, 7.33579f, 11.5858f, 7f, 12f, 7f)
                close()
            }
        }.build()

        return _FluentuiSystemIconsAddCircle!!
    }

private var _FluentuiSystemIconsAddCircle: ImageVector? = null