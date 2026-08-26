package com.example.cookingbook.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

/*
MIT License

Copyright (c) 2022 WorkOS

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
val RadixMinus: ImageVector
    get() {
        if (_RadixMinus != null) return _RadixMinus!!

        _RadixMinus = ImageVector.Builder(
            name = "minus",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 15f,
            viewportHeight = 15f
        ).apply {
            path(
                fill = SolidColor(Color.Black)
            ) {
                moveTo(12.25f, 7f)
                curveTo(12.5261f, 7f, 12.75f, 7.22386f, 12.75f, 7.5f)
                curveTo(12.75f, 7.77614f, 12.5261f, 8f, 12.25f, 8f)
                horizontalLineTo(2.75f)
                curveTo(2.47386f, 8f, 2.25f, 7.77614f, 2.25f, 7.5f)
                curveTo(2.25f, 7.22386f, 2.47386f, 7f, 2.75f, 7f)
                horizontalLineTo(12.25f)
                close()
            }
        }.build()

        return _RadixMinus!!
    }

private var _RadixMinus: ImageVector? = null