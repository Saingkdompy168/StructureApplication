package com.example.structureapplication.callbacks

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