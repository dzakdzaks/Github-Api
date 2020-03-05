package com.dzakdzaks.github_api.common

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.dzakdzaks.github_api.MyApp


/**
 * ==================================//==================================
 * ==================================//==================================
 * Created on Thursday, 05 March 2020 at 10:18.
 * Project Name => Github_API
 * Package Name => com.dzakdzaks.github_api.common
 * ==================================//==================================
 * ==================================//==================================
 */
class NetworkChangesReceiver constructor(private val networkChangesCallback: NetworkChangesCallback) :
    BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        networkChangesCallback.onNetworkChanged(isOnline())
    }

    fun isOnline(): Boolean {
        var result = false
        val connectivityManager: ConnectivityManager =
            MyApp.appContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val activeNetwork =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }
            }
        }
        return result
    }

    interface NetworkChangesCallback {
        fun onNetworkChanged(isOnline: Boolean)
    }

}