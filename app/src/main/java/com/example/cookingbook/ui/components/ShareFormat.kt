package com.example.cookingbook.ui.components

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.pdf.PdfDocument
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

enum class ShareFormat { IMAGE, PDF }

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

fun openRecipeFile(context: Context, bitmap: ImageBitmap, fileName: String, format: ShareFormat){
    val androidBitmap = bitmap.asAndroidBitmap()
    val (file, mimeType) = when(format){
        ShareFormat.IMAGE -> saveAsImage(context, androidBitmap, fileName) to "image/png"
        ShareFormat.PDF -> saveAsPdf(context, androidBitmap, fileName) to "application/pdf"
    }

    val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
    val intent = Intent(Intent.ACTION_VIEW).apply {
        type = mimeType
        putExtra(Intent.EXTRA_STREAM, uri)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    context.startActivity(Intent.createChooser(intent, "Ouvrir avec"))
}

private fun saveAsImage(context: Context, bitmap: Bitmap, fileName: String): File{
    val dir = File(context.cacheDir, "images").apply { mkdirs() }
    val file = File(dir, "$fileName.png")
    FileOutputStream(file).use {out -> bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)}
    return file
}

private fun saveAsPdf(context: Context, bitmap: Bitmap, fileName: String): File{
    val document = PdfDocument()
    val pageInfo = PdfDocument.PageInfo.Builder(bitmap.width, bitmap.height, 1).create()
    val page = document.startPage(pageInfo)
    page.canvas.drawBitmap(bitmap, 0f, 0f, null)
    document.finishPage(page)

    val dir = File(context.cacheDir, "pdfs").apply { mkdirs() }
    val file = File(dir, "$fileName.pdf")
    FileOutputStream(file).use {out -> document.writeTo(out)}
    document.close()
    return file
}