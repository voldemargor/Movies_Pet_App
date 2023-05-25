package com.example.moviespetapp.presentation.contract

import android.util.Log
import androidx.fragment.app.Fragment

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {

    fun displayMainScreen()

    fun displayMoviesListScreen(genreName: String)

    fun displayMovieDetailsScreen(movieId: Int, movieName: String)

    fun displayBookmarksScreen()

    fun displaySearchScreen()

    fun displaySearchResultsScreen()

    fun goBack()

    fun setScreenTitle(title: String)

    fun showToast(message: String)

    fun log(message: String)
}