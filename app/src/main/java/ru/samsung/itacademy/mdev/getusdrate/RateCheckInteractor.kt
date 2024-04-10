package ru.samsung.itacademy.mdev.getusdrate

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class RateCheckInteractor {
    val networkClient = NetworkClient()

    suspend fun requestRate(): String {
        //Write your code here:
        return ""
    }

    private fun parseRate(jsonString: String): String {
        try {
            return JSONObject(jsonString)
                .getJSONObject("exchange_rates")
                .getString("RUB")
        } catch (e: Exception) {
            Log.e("RateCheckInteractor", "", e)
        }
        return ""
    }
}