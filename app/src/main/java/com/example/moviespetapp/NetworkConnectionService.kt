package com.example.moviespetapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import com.example.moviespetapp.presentation.dialog.ExceptionDialog
import com.example.moviespetapp.presentation.dialog.NoInternetDialog

class NetworkConnectionService(private val activity: AppCompatActivity) {

    fun hasInternetConnection(): Boolean {
        val connectivityManager =
            activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    fun shouldDisplayNoInternet(): Boolean {
        if (!hasInternetConnection()) {
            displayNoInternetDialog()
            return true
        }
        return false
    }

    fun displayNoInternetDialog() {
        NoInternetDialog.newInstance().show(activity.supportFragmentManager, ExceptionDialog.TAG)
    }

}
