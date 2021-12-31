package com.example.structureapplication.extension

import androidx.lifecycle.LiveData
import com.example.structureapplication.util.SingleLiveEvent

fun <T> LiveData<T>.toSingleEvent(): LiveData<T> {
    val result = SingleLiveEvent<T>()
    result.addSource(this) {
        result.value = it
    }
    return result
}