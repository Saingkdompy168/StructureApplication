package com.example.structureapplication.socket

import android.content.Context
import android.util.Log
import com.example.structureapplication.util.SingletonHolder
import org.json.JSONObject
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.engineio.client.transports.WebSocket
import java.util.*

class AuctionSocket constructor(private val context: Context) {

    private var mSocketAuction: SocketAuction? = null
    private var mSocket: Socket? = null

    fun initSocket() {
        mSocket = IO.socket("http:12.0.0.20/auth", getOptions())

        mSocket?.on(Socket.EVENT_CONNECT) { args ->
            Log.d("AuctionSocket", "EVENT_CONNECT : " + Arrays.toString(args))
        }?.on(Socket.EVENT_DISCONNECT) { args ->
            Log.d("AuctionSocket", "EVENT_DISCONNECT : " + Arrays.toString(args))
        }?.on(Socket.EVENT_CONNECT_ERROR) { args ->
            Log.d("AuctionSocket", "EVENT_CONNECT_ERROR : " + Arrays.toString(args))
        }?.on(Socket.EVENT_ERROR) { args ->
            Log.d("AuctionSocket", "EVENT_ERROR : " + Arrays.toString(args))
        }?.on(Socket.EVENT_CONNECT_TIMEOUT) { args ->
            Log.d("AuctionSocket", "EVENT_CONNECT_TIMEOUT : " + Arrays.toString(args))
        }?.on(Socket.EVENT_RECONNECT) { args ->
            Log.d("AuctionSocket", "EVENT_RECONNECT : " + Arrays.toString(args))
        }?.on("AUCTION") {
            onReceiveSocket("AUCTION", JSONObject(it[0].toString()))
        }?.on("AUCTION_BID") {
            onReceiveSocket("AUCTION_BID", JSONObject(it[0].toString()))
        }?.on("AUCTION_POINT") {
            onReceiveSocket("AUCTION_POINT", JSONObject(it[0].toString()))
        }
        mSocket?.connect()
    }

    private fun getOptions(): IO.Options {
        val options = IO.Options()
        options.forceNew = false
        options.multiplex = false
        options.reconnection = true
        options.transports = arrayOf(WebSocket.NAME)
        options.timeout = 5000
        options.query = "token={}"
        return options
    }

    fun onAuctionListener(onSocketAuction: SocketAuction?) {
        if (onSocketAuction != null) {
            mSocketAuction = onSocketAuction
        }
    }

    fun onReceiveSocket(eventName: String, data: JSONObject) {
        Log.d(eventName, "===> ${data.toString(1)}")
        mSocketAuction?.onAuctionListener(eventName, data)
    }

    fun cancelSockets() {
        mSocket?.off("AUCTION")
        mSocket?.off("AUCTION_BID")
        mSocket?.off("AUCTION_POINT")
        mSocket?.disconnect()
    }

    interface SocketAuction {
        fun onAuctionListener(eventName: String, data: JSONObject)
    }

    companion object : SingletonHolder<AuctionSocket, Context>(::AuctionSocket)
}