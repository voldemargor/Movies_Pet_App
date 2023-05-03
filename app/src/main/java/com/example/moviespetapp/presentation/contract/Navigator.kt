package com.example.moviespetapp.presentation.contract

import androidx.fragment.app.Fragment

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {

    fun displayMainScreen()

    fun displayMovieDetailsScreen(movieId: Int)

    fun displayBookmarksScreen()

    fun displaySearchScreen()

    fun displaySearchResultsScreen()

    fun goBack()

}