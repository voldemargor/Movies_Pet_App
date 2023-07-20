package com.example.moviespetapp.domain

import java.lang.Exception

sealed class DataLoadingResult {

    class Success<T>(val data: T?) : DataLoadingResult()
    class Failed(val exception: Exception) : DataLoadingResult()

}