package com.example.cookingbook.ui.data

import android.content.Context
import android.net.Uri
import java.io.File
import java.util.UUID

/**
 * Copies the image at [uri] (typically returned by a photo picker launched from
 * [com.example.cookingbook.ui.components.WidgetImg]) into the app's private internal storage
 * ([Context.getFilesDir]).
 *
 * This is necessary because a picked 'content://' URI's permission grant is often only valid for the
 * current session (persisting the recipe with that raw URI could break the image reference after the
 * app restarts or the picker's grant expires). Copying the bytes into internal storage gives a stable,
 * app-owned file path that survives across sessions and is safe to store long-term in [Recette.image].
 *
 * The copy is renamed to a random 'recette_<uuid>.jpg', so collisions with an existing file, and any
 * dependency on the original file name, are avoided entirely.
 *
 * @param context used to resolve [uri]'s content and to locate internal storage.
 * @param uri the source image URI to copy, e.g. from a 'PickVisualMedia' result.
 * @return the absolute path of the copied file on success, or 'null' if the URI couldn't be opened
 * or the copy failed for any reason (the underlying exception is only logged via [Throwable.printStackTrace],
 * not propagated).
 */
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

/**
 * Deletes the internal-storage image file at [path], if any. Safe to call with a blank path (does
 * nothing) or a path that no longer exists on disk.
 *
 * @param path the internal-storage file path to delete, typically a [Recette.image] value.
 */
fun deleteInternalImage(path: String){
    if (path.isBlank()) return
    try {
        File(path).delete()
    }
    catch (e: Exception){
        e.printStackTrace()
    }
}