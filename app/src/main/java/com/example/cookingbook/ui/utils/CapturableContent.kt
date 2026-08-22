package com.example.cookingbook.ui.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.layer.GraphicsLayer
import androidx.compose.ui.graphics.layer.drawLayer

/**
 * Wraps [content] in a [Box] whose drawn output is also recorded into the provided [graphicsLayer],
 * making it possible to capture that content as a [androidx.compose.ui.graphics.ImageBitmap] later
 * on (e.g. via 'graphicsLayer.toImageBitmap()').
 *
 * This is the core building block behind the recipe sharing feature: the recipe detail screen renders
 * an off-screen copy of [RecipeContent] through this composable, then converts the resulting bitmap
 * into a PNG or PDF file for sharing (see 'ShareFormat.kt').
 *
 * How it works: [Modifier.drawWithCache] gives access to the draw phase.
 * Inside [onDrawWithContent] [androidx.compose.ui.draw.CacheDrawScope.onDrawWithContent],
 * [graphicsLayer].record captures the normal draw content of this composable into the layer, and
 * [drawLayer] then draws that layer to the actual canvas (so the content is drawn on screen exactly
 * as it would be without this wrapper, while also being available for capture through [graphicsLayer]).
 *
 * @param graphicsLayer the layer used to record the content's drawing instructions, so it can be
 * captured as a bitmap on demand. Typically created with [androidx.compose.ui.graphics.rememberGraphicsLayer].
 * @param content the composable content to render and make capturable.
 */

@Composable
fun CapturableContent(graphicsLayer: GraphicsLayer, content: @Composable () -> Unit){
    Box(
        modifier = Modifier.drawWithCache {
            onDrawWithContent {
                // Record the content's draw operations into the layer
                graphicsLayer.record{ this@onDrawWithContent.drawContent()}
                // Draw the recorded layer to the actual canvas (normal rendering)
                drawLayer(graphicsLayer)
            }
        }
    ){
        content()
    }
}