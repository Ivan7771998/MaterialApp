package com.dev777popov.materialapp.tools

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import com.dev777popov.materialapp.BuildConfig
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class DataStoreHelper(private val context: Context) {

    private lateinit var currentPhotoPath: String

    @SuppressLint("SimpleDateFormat")
    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    fun getPhotoURL(): Uri? {
        val photoFile: File? = try {
            createImageFile()
        } catch (ex: IOException) {
            ShowInfo.toast(context, "Ошибка создания файла!")
            null
        }
        if (photoFile != null) {
            return FileProvider.getUriForFile(
                context,
                BuildConfig.APPLICATION_ID + ".provider", photoFile
            )
        }
        return null
    }

    fun deleteImageFile(fileName: String): Boolean {
        val url = Uri.parse(fileName).path
        if (url != null) {
            val file = File(url)
            return file.delete()
        }
        return false
    }

    fun getPhotoList(): MutableList<String> {
        val result: MutableList<String> = ArrayList()
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        storageDir?.let { dir ->
            dir.listFiles()?.let { list ->
                list.forEach { fileName ->
                    fileName?.let {
                        val fileURI: String = Uri.fromFile(it).toString()
                        result.add(fileURI)
                    }
                }
            }
        }
        return result
    }

    fun getCurrentPhotoPath(): String {
        return currentPhotoPath
    }
}