package com.example.moviespetapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviespetapp.data.network.ConnectivityObserver
import com.example.moviespetapp.data.network.NetworkConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigatorActivityViewModel @Inject constructor() : ViewModel() {

    @Inject lateinit var connectivityObserver: NetworkConnectivityObserver

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