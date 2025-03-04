package ru.samsung.itacademy.mdev.getusdrate

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONObject

class RateCheckInteractor {
    val networkClient = NetworkClient()

    suspend fun requestRate(): String {
        val json = GlobalScope.async {
            networkClient.request(MainViewModel.USD_RATE_URL)
        }
        return json.await()?.let(::parseRate) ?: ""
    }

    private fun parseRate(jsonString: String): String {
        return try {
            JSONObject(jsonString)
                .getJSONObject("exchange_rates")
                .getString("RUB")
                .take(10)
        } catch (e: Exception) {
            Log.e("RateCheckInteractor", "", e)
            ""
        }
    }
}