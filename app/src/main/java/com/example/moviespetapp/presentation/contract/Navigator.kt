package com.example.moviespetapp.presentation.contract

import androidx.fragment.app.Fragment
import com.example.moviespetapp.data.network.ConnectivityObserver
import com.example.moviespetapp.presentation.JobStatus
import com.example.moviespetapp.presentation.Screen

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {

    fun displayScreen(screen: Screen)

    //fun displayScreen(screen: Int)
    //fun displayScreen(screen: Int, genreName: String)
    //fun displayScreen(screen: Int, movieId: Int, movieName: String)

    //fun displayMainScreen()
    //fun displayMoviesListScreen(genreName: String)
    //fun displayMovieDetailsScreen(movieId: Int, movieName: String)
    //fun displayBookmarksScreen()
    //fun displaySearchScreen()

    fun goBack()

    fun setScreenTitle(title: String)

    fun showExceptionDialog(message: String)

    fun finish()

    fun tryReconnect()

    fun toast(message: String)

    fun log(message: String)

}