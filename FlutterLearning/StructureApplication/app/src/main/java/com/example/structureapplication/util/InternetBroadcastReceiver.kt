package com.example.structureapplication.util

import android.content.BroadcastReceiver
import android.content.Context
import com.example.structureapplication.util.InternetBroadcastReceiver.OnInternetConnectionChangeListener
import android.content.Intent
import com.example.structureapplication.util.InternetBroadcastReceiver
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.telephony.TelephonyManager
import android.util.Log

class InternetBroadcastReceiver : BroadcastReceiver() {
    private var onInternetConnectionChangeListener: OnInternetConnectionChangeListener? = null
    fun setOnInternetConnectionChangeListener(onInternetConnectionChangeListener: OnInternetConnectionChangeListener?) {
        this.onInternetConnectionChangeListener = onInternetConnectionChangeListener
    }

    override fun onReceive(context: Context, intent: Intent) {
        val status = getConnectivityStatusString(context)
        Log.i("CONNECTION_CHANGE", "STATUS$status")
        if (status == NOT_CONNECT) {
            Log.i("CONNECTION_CHANGE ", "INTERNET DISCONNECT")
            //  check !isConnected , cuz don't want to call back redundant
            if (onInternetConnectionChangeListener != null) {
                onInternetConnectionChangeListener!!.onDisconnected()
            }
        } else {
            Log.i("CONNECTION_CHANGE ", "INTERNET CONNECTED")
            //  check !isConnected , cuz don't want to call back redundant
            if (onInternetConnectionChangeListener != null) {
                onInternetConnectionChangeListener!!.onConnected()
            }
        }
    }

    interface OnInternetConnectionChangeListener {
        fun onDisconnected()
        fun onConnected()
    }

    companion object {
        private const val TYPE_WIFI = 1
        private const val TYPE_MOBILE = 2
        private const val TYPE_NOT_CONNECTED = 0
        private const val CONNECT_TO_WIFI = "WIFI"
        private const val CONNECT_TO_MOBILE = "MOBILE"
        private const val NOT_CONNECT = "NOT_CONNECT"
        const val CONNECTIVITY_ACTION = "android.net.conn.CONNECTIVITY_CHANGE"

        private fun getConnectivityStatus(context: Context): Int {
            val cm = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            if (null != activeNetwork) {
                if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) return TYPE_WIFI
                if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) return TYPE_MOBILE
            }
            return TYPE_NOT_CONNECTED
        }

        private fun isInternetAvailable(context: Context): Boolean {
            var result = false
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val networkCapabilities = connectivityManager.activeNetwork ?: return false
                val actNw =
                    connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
                result = when {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
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

        fun getConnectivityStatusString(context: Context): String? {
            val conn = isInternetAvailable(context)
            var status: String? = null
            status = when (conn) {
                true -> {
                    //status = "Wifi enabled";
                    CONNECT_TO_WIFI
                    //status = "Mobile data enabled";
                    println(CONNECT_TO_MOBILE)
                    getNetworkClass(context)
                }
//                true -> {
//                    //status = "Mobile data enabled";
//                    println(CONNECT_TO_MOBILE)
//                    getNetworkClass(context)
//                }
                false -> {
                    NOT_CONNECT
                }
            }
            return status
        }

        private fun getNetworkClass(context: Context): String {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = cm.activeNetworkInfo
            if (info == null || !info.isConnected) return "-" //not connected
            if (info.type == ConnectivityManager.TYPE_WIFI) return "WIFI"
            if (info.type == ConnectivityManager.TYPE_MOBILE) {
                return when (info.subtype) {
                    TelephonyManager.NETWORK_TYPE_HSPAP -> "3G"
                    TelephonyManager.NETWORK_TYPE_LTE -> "4G"
                    else -> "UNKNOWN"
                }
            }
            return "UNKNOWN"
        }
    }
}