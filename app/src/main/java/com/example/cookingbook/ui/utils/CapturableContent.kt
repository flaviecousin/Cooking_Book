package com.example.cookingbook.ui.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.layer.GraphicsLayer
import androidx.compose.ui.graphics.layer.drawLayer

@Composable
fun CapturableContent(graphicsLayer: GraphicsLayer, content: @Composable () -> Unit){
    Box(
        modifier = Modifier.drawWithCache {
            onDrawWithContent {
                graphicsLayer.record{ this@onDrawWithContent.drawContent()}
                drawLayer(graphicsLayer)
            }
        }
    ){
        content()
    }
}