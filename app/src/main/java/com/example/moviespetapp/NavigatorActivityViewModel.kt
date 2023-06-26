package com.example.moviespetapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigatorActivityViewModel @Inject constructor() : ViewModel() {

    private val _displaySplash = MutableLiveData<Boolean>()
    val displaySplash: LiveData<Boolean> get() = _displaySplash

    init {
        _displaySplash.value = true
        viewModelScope.launch {
            delay(2_000)
            _displaySplash.value = false
        }
    }

}