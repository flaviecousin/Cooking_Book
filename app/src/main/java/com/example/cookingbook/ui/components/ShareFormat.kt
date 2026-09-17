package com.example.cookingbook.ui.components

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.pdf.PdfDocument
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.core.content.FileProvider
import androidx.core.graphics.createBitmap
import com.example.cookingbook.ui.theme.WarmCream
import java.io.File
import java.io.FileOutputStream

/**
 * Export format for a shared recipe: a flattened [IMAGE] (PNG) or a single-page [PDF].
 */
enum class ShareFormat { IMAGE, PDF }

/**
 * Saves [bitmap] as [format] and opens the system share sheet for it via an implicit [Intent.ACTION_SEND],
 * using a [FileProvider]-backed 'content://' URI (required since Android 7+ forbids sharing raw 'file://'
 * paths across app, see 'file_paths.xml' for the provider's path config).
 *
 * @param context used to save the file and start the share intent.
 * @param bitmap the captured recipe content, typically produced by [com.example.cookingbook.ui.utils.CapturableContent]'s
 * 'GraphicsLayer.toImageBitmap()'.
 * @param fileName base file name (without extension) for the exported file.
 * @param format which format to export as; determines both the file extension and the shared MIME type.
 */
fun shareRecipe(context: Context, bitmap: ImageBitmap, fileName: String, format: ShareFormat){
    val androidBitmap = bitmap.asAndroidBitmap()
    val (file, mimeType) = when(format){
        ShareFormat.IMAGE -> saveAsImage(context, androidBitmap, fileName) to "image/png"
        ShareFormat.PDF -> saveAsPdf(context, androidBitmap, fileName) to "application/pdf"
    }

    val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = mimeType
        putExtra(Intent.EXTRA_STREAM, uri)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    context.startActivity(Intent.createChooser(intent, "Partager la recette"))
}

/**
 * Saves [bitmap] as [format] and opens it directly via an implicit [Intent.ACTION_VIEW], letting the
 * user pick an installed app capable of displaying that file type, rather than routing through the
 * share sheet.
 *
 * @param context used to save the file and start the view intent.
 * @param bitmap the captured recipe content to export.
 * @param fileName base file name (without extension) for the exported file.
 * @param format which format to export as.
 */
fun openRecipeFile(context: Context, bitmap: ImageBitmap, fileName: String, format: ShareFormat){
    val androidBitmap = bitmap.asAndroidBitmap()
    val (file, mimeType) = when(format){
        ShareFormat.IMAGE -> saveAsImage(context, androidBitmap, fileName) to "image/png"
        ShareFormat.PDF -> saveAsPdf(context, androidBitmap, fileName) to "application/pdf"
    }

    val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
    val intent = Intent(Intent.ACTION_VIEW).apply {
        setDataAndType(uri, mimeType)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    context.startActivity(Intent.createChooser(intent, "Ouvrir avec"))
}

/**
 * Renders [bitmap] onto an opaque [WarmCream]-colored background and saves the result as a PNG under
 * 'context.cacheDir/images/'.
 *
 * Delegates the background compositing step to [applyWarmCreamBackground] to guarantee visual
 * consistency with [saveAsPdf].
 *
 * @param context used to resolve the app's cache directory.
 * @param bitmap the source bitmap to export, potentially with transparent regions.
 * @param fileName base file name (without extension).
 * @return the saved PNG [File].
 */
private fun saveAsImage(context: Context, bitmap: Bitmap, fileName: String): File{
    val bitmapWithBackground = applyWarmCreamBackground((bitmap))
    val dir = File(context.cacheDir, "images").apply { mkdirs() }
    clearCacheDir(dir)
    val file = File(dir, "$fileName.png")
    FileOutputStream(file).use{out ->
        bitmapWithBackground.compress(Bitmap.CompressFormat.PNG, 100,out)
    }
    return file
}

/**
 * Renders [bitmap] onto a single-page PDF sized exactly to the bitmap's dimensions, and saves it
 * under 'context.cacheDir/pdfs/'.
 *
 * Uses [applyWarmCreamBackground] prior to rendering so that recipes lacking a photo (or containing
 * transparent layout regions) displays an opaque [WarmCream] background matching [saveAsImage],
 * preventing PDF viewers from rendering these areas as plain default white.
 *
 * @param context used to resolve the app's cache directory.
 * @param bitmap the source bitmap to export.
 * @param fileName base file name (without extension).
 * @return the saved PDF [File].
 */
private fun saveAsPdf(context: Context, bitmap: Bitmap, fileName: String): File{
    val bitmapWithBackground = applyWarmCreamBackground(bitmap)

    val document = PdfDocument()
    val pageInfo = PdfDocument.PageInfo.Builder(bitmapWithBackground.width, bitmapWithBackground.height, 1).create()
    val page = document.startPage(pageInfo)
    page.canvas.drawBitmap(bitmapWithBackground, 0f, 0f, null)
    document.finishPage(page)

    val dir = File(context.cacheDir, "pdfs").apply { mkdirs() }
    clearCacheDir(dir)
    val file = File(dir, "$fileName.pdf")
    FileOutputStream(file).use {out -> document.writeTo(out)}
    document.close()
    return file
}

/**
 * Composites the source [bitmap] over an opaque [WarmCream]-colored background canvas.
 *
 * Handles 2 critical transformations for exported recipe content:
 * 1. Defensively converts [Bitmap.Config.HARDWARE] instances (produced by Compose layer captures)
 *    into software-backed [Bitmap.Config.ARGB_8888] bitmaps that can be read by [android.graphics.Canvas].
 * 2. Replaces any transparent regions in the source capture (e.g. recipes without top photos) with
 *    the app's signature cream background, ensuring identical output across PNG and PDF formats.
 *
 * @param bitmap the source bitmap captured from the Compose hierarchy.
 * @return a software [Bitmap] with an opaque [WarmCream] background applied.
 */
private fun applyWarmCreamBackground(bitmap: Bitmap): Bitmap{
    val softwareBitmap = if(bitmap.config == Bitmap.Config.HARDWARE){
        bitmap.copy(Bitmap.Config.ARGB_8888, false)
    } else{
        bitmap
    }
    val backgroundColor = WarmCream.toArgb()
    return createBitmap(softwareBitmap.width, softwareBitmap.height).apply {
        val canvas = android.graphics.Canvas(this)
        canvas.drawColor(backgroundColor)
        canvas.drawBitmap(softwareBitmap, 0f, 0f, null)
    }
}

/**
 * Deletes every file currently in [dir], if it exists. Used to prevent [saveAsImage] and [saveAsPdf]
 * from silently accumulating orphaned exports across multiple shares of different recipes.
 */
private fun clearCacheDir (dir: File){
    if (dir.exists()){
        dir.listFiles()?.forEach { it.delete() }
    }
}