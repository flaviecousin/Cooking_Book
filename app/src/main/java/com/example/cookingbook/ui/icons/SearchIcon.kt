package com.example.cookingbook.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val FluentuiSystemIconsSearch: ImageVector
    get() {
        if (_FluentuiSystemIconsSearch != null) return _FluentuiSystemIconsSearch!!

        _FluentuiSystemIconsSearch = ImageVector.Builder(
            name = "search",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color.Black)
            ) {
                moveTo(16.1017f, 17.1624f)
                curveTo(14.717f, 18.3101f, 12.9391f, 19f, 11f, 19f)
                curveTo(6.58172f, 19f, 3f, 15.4183f, 3f, 11f)
                curveTo(3f, 6.58172f, 6.58172f, 3f, 11f, 3f)
                curveTo(15.4183f, 3f, 19f, 6.58172f, 19f, 11f)
                curveTo(19f, 12.9391f, 18.3101f, 14.717f, 17.1624f, 16.1018f)
                lineTo(21.7803f, 20.7197f)
                curveTo(22.0732f, 21.0126f, 22.0732f, 21.4874f, 21.7803f, 21.7803f)
                curveTo(21.4874f, 22.0732f, 21.0125f, 22.0732f, 20.7196f, 21.7803f)
                lineTo(16.1017f, 17.1624f)
                close()
                moveTo(17.5f, 11f)
                curveTo(17.5f, 7.41015f, 14.5899f, 4.5f, 11f, 4.5f)
                curveTo(7.41015f, 4.5f, 4.5f, 7.41015f, 4.5f, 11f)
                curveTo(4.5f, 14.5899f, 7.41015f, 17.5f, 11f, 17.5f)
                curveTo(14.5899f, 17.5f, 17.5f, 14.5899f, 17.5f, 11f)
                close()
            }
        }.build()

        return _FluentuiSystemIconsSearch!!
    }

private var _FluentuiSystemIconsSearch: ImageVector? = null