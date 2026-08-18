package com.example.cookingbook.ui.data

import android.content.Context
import android.net.Uri
import java.io.File
import java.util.UUID

fun copyImageToInternalStorage(context: Context, uri: Uri): String? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri) ?: return null
        val fileName = "recette_${UUID.randomUUID()}.jpg"
        val outputFile = File(context.filesDir, fileName)

        inputStream.use { input ->
            outputFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }
        outputFile.absolutePath
    }
    catch (e: Exception){
        e.printStackTrace()
        null
    }
}