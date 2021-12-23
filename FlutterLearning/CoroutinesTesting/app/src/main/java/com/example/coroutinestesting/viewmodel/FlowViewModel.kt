package com.example.coroutinestesting.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class FlowViewModel : ViewModel() {

    var countDownFlow = flow<Int> {
        val startingValue = 10
        var currentValue = startingValue
        emit(startingValue)
        if (currentValue > 0) {
            delay(1000L)
            currentValue--
            emit(currentValue)
        }
    }
}