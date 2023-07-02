package com.example.moviespetapp.presentation.contract

import androidx.fragment.app.Fragment

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {

    fun displayScreen(screen: Screen)

    fun setScreenTitle(title: String)

    fun goBack()

    fun showExceptionDialog(message: String)

    fun closeApp()

    fun tryReconnect()

    fun toast(message: String)

    fun log(message: String)

}