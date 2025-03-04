package ru.samsung.itacademy.mdev.getusdrate

import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val usdRate = MutableLiveData<String>()
    val rateCheckInteractor = RateCheckInteractor()

    fun onCreate() {
        refreshRate()
    }

    fun onRefreshClicked() {
        refreshRate()
    }

    private fun refreshRate() {
        GlobalScope.launch(Dispatchers.Main) {
            val rate = rateCheckInteractor.requestRate()
            usdRate.value = rate
        }
    }

    companion object {
        const val TAG = "MainViewModel"
        const val API_KEY = "a5949c33ac6646e79988242ad81e2ab5"
        const val USD_RATE_URL = "https://exchange-rates.abstractapi.com/v1/live/?api_key=$API_KEY&base=USD&target=RUB"
    }
}