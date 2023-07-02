package com.example.moviespetapp.presentation.contract

import androidx.annotation.IdRes

interface BottomNavItem {

    @IdRes
    fun getBottomNavItemId() : Int

    fun handleDoubleBottomMenuClick()

}