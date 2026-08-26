package com.example.cookingbook.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

/*
MIT License

Copyright (c) Tailwind Labs, Inc.

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
val HeroiconsBookOpen: ImageVector
    get() {
        if (_HeroiconsBookOpen != null) return _HeroiconsBookOpen!!

        _HeroiconsBookOpen = ImageVector.Builder(
            name = "book-open",
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
                moveTo(12f, 6.042f)
                arcTo(8.967f, 8.967f, 0f, false, false, 6f, 3.75f)
                curveToRelative(-1.052f, 0f, -2.062f, 0.18f, -3f, 0.512f)
                verticalLineToRelative(14.25f)
                arcTo(8.987f, 8.987f, 0f, false, true, 6f, 18f)
                curveToRelative(2.305f, 0f, 4.408f, 0.867f, 6f, 2.292f)
                moveToRelative(0f, -14.25f)
                arcToRelative(8.966f, 8.966f, 0f, false, true, 6f, -2.292f)
                curveToRelative(1.052f, 0f, 2.062f, 0.18f, 3f, 0.512f)
                verticalLineToRelative(14.25f)
                arcTo(8.987f, 8.987f, 0f, false, false, 18f, 18f)
                arcToRelative(8.967f, 8.967f, 0f, false, false, -6f, 2.292f)
                moveToRelative(0f, -14.25f)
                verticalLineToRelative(14.25f)
            }
        }.build()

        return _HeroiconsBookOpen!!
    }

private var _HeroiconsBookOpen: ImageVector? = null