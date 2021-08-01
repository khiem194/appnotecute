package com.kdnt.notecute

import java.text.SimpleDateFormat
import java.util.*

object Constants {
    const val KEY_TASK = "key_task"
    const val DATABASE_NAME = "task_database"
    const val MY_REQUEST_CODE = 11

    private const val NAME_FILE_PREFIX = "nshow"
    fun getNameImage(): String {
        val timeStamp: String =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        return "${NAME_FILE_PREFIX}_$timeStamp"
    }

//    fun getPathDirSaveImage(folderName: String): String {
//        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            Environment.DIRECTORY_PICTURES + File.separator + folderName
//        } else {
//            Environment.getExternalStorageDirectory().absolutePath + File.separator + Environment.DIRECTORY_PICTURES + File.separator + folderName
//        }
//    }
}