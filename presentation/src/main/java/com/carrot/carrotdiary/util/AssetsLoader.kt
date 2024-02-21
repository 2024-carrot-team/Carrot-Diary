package com.carrot.carrotdiary.util

import android.content.Context

class AssetsLoader(private val context: Context) {
    fun getJsonString(fileName: String): String? {
        return kotlin.runCatching {
            loaderAsset(fileName)
        }.getOrNull()
    }

    private fun loaderAsset(fileName: String): String {
        return context.assets.open(fileName).use { inputStream ->
            val size = inputStream.available()
            val bytes = ByteArray(size)
            inputStream.read(bytes)
            String(bytes)

        }
    }
}