package com.carrot.presentation

import com.carrot.presentation.model.Daily
import com.carrot.presentation.util.AssetsLoader
import com.google.gson.Gson

class DailyAssetDataSource(private val assetsLoader: AssetsLoader) {

    private val gson = Gson()

    fun getHomeData(): Daily? {
        return assetsLoader.getJsonString("home.json")?.let { homeJsonString ->
            gson.fromJson(homeJsonString, Daily::class.java)
        }
    }
}