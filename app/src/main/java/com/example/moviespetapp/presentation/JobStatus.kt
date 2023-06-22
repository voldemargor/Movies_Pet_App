package com.example.moviespetapp.presentation

import com.example.moviespetapp.domain.entity.Movie

sealed class JobStatus

object Loading : JobStatus()
object Canceled : JobStatus()
class Error(val message: String?) : JobStatus()
class Result(val movies: List<Movie>) : JobStatus()
