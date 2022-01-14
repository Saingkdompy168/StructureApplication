package com.example.structureapplication.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.LiveData
import com.example.structureapplication.util.SingleLiveEvent

fun <T> LiveData<T>.toSingleEvent(): LiveData<T> {
    val result = SingleLiveEvent<T>()
    result.addSource(this) {
        result.value = it
    }
    return result
}

fun ViewGroup.inflate(@LayoutRes res: Int) : View {
    return LayoutInflater.from(this.context).inflate(res, this, false)
}