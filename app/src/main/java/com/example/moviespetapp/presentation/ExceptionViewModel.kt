package com.example.moviespetapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class ExceptionViewModel : ViewModel() {

    private val _hasException = MutableLiveData<String>()
    val hasException: LiveData<String> get() = _hasException

    protected val exceptionHandler = CoroutineExceptionHandler() { _, throwable ->
        viewModelScope.launch(Dispatchers.Main) { _hasException.value = throwable.message }
    }

}