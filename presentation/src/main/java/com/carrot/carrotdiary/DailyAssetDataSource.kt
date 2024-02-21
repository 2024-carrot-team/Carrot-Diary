package com.carrot.carrotdiary

import com.carrot.carrotdiary.model.Daily
import com.carrot.carrotdiary.util.AssetsLoader
import com.google.gson.Gson

class DailyAssetDataSource(private val assetsLoader: AssetsLoader) {

    private val gson = Gson()

    fun getHomeData(): Daily? {
        return assetsLoader.getJsonString("home.json")?.let { homeJsonString ->
            gson.fromJson(homeJsonString, Daily::class.java)
        }
    }
}