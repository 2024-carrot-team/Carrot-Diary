package com.carrot.presentation

import com.carrot.presentation.model.MainDiaries
import com.carrot.presentation.util.AssetsLoader
import com.google.gson.Gson

class DailyAssetDataSource(private val assetsLoader: AssetsLoader) {

    private val gson = Gson()

    fun getHomeData(): MainDiaries? {
        return assetsLoader.getJsonString("home.json")?.let { homeJsonString ->
            gson.fromJson(homeJsonString, MainDiaries::class.java)
        }
    }
}