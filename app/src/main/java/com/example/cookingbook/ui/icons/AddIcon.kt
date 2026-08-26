package com.example.cookingbook.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

/*
MIT License

Copyright (c) 2020 Microsoft Corporation

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
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