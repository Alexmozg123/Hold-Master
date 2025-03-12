package ru.bortsov.holdmaster.composeapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class MainViewModel(
    sayHello: String,
) : ViewModel() {

    private val _timerState = MutableStateFlow(0)
    val timer = _timerState.asStateFlow()

    init {
        startTimer()
        println(sayHello)
    }

    private fun startTimer() {
        viewModelScope.launch {
            while (true) {
                _timerState.value++
                delay(1000)
            }
        }
    }
}