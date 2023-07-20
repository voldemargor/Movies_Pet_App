package com.example.moviespetapp.presentation.mainscreen

import com.example.moviespetapp.presentation.movieslist.MoviesListAdapter

class MainFragmentAdapters {

    val New by lazy { MoviesListAdapter(MoviesListAdapter.ListType.HORIZONTAL) }
    val Soon by lazy { MoviesListAdapter(MoviesListAdapter.ListType.HORIZONTAL) }
    val Popular by lazy { MoviesListAdapter(MoviesListAdapter.ListType.HORIZONTAL) }
    val Fiction by lazy { MoviesListAdapter(MoviesListAdapter.ListType.HORIZONTAL) }
    val Comedy by lazy { MoviesListAdapter(MoviesListAdapter.ListType.HORIZONTAL) }
    val Horror by lazy { MoviesListAdapter(MoviesListAdapter.ListType.HORIZONTAL) }
    val ForKids by lazy { MoviesListAdapter(MoviesListAdapter.ListType.HORIZONTAL) }

}