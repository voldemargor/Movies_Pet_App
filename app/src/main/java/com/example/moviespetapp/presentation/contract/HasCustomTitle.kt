package com.example.moviespetapp.presentation.contract

import androidx.annotation.StringRes

interface HasCustomTitle {

    @StringRes
    fun getTitleRes(): Int

}