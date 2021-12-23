package com.example.coroutinestesting.model

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class JoinCoroutines {
    suspend fun testing() {
        Log.d("MyTag", "Top ${Thread.currentThread().name}")
        coroutineScope {
            val job = launch(Dispatchers.IO) {
                Log.d("MyTag", "Before Delay ${Thread.currentThread().name}")
                delay(1000)
                Log.d("MyTag", "After ${Thread.currentThread().name}")
            }
            job.join()
        }
        Log.d("MyTag", "Bottom ${Thread.currentThread().name}")
    }
}

fun interface KRunnable {
    fun invoke()
}