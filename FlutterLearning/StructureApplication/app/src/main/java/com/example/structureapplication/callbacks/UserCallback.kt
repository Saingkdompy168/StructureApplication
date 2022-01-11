package com.example.structureapplication.callbacks

import kotlin.coroutines.suspendCoroutine

class UserCallback {
    private lateinit var connect: ConnectionCallBack

    fun connection(){
        val userCallback=UserCallback()
    }
}




interface ConnectionCallBack {
    fun onConnection()
    fun onError()
}