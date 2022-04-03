package com.seniah.news.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectivityHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {
    operator fun invoke(listener: NetworkListener) {

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager
            .registerNetworkCallback(
                NetworkRequest.Builder().build(),
                object : ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network) {
                        listener.available()
                    }

                    override fun onLost(network: Network) {
                        listener.error()
                    }
                }
            )
    }

    interface NetworkListener {
        fun available()
        fun error()
    }
}
