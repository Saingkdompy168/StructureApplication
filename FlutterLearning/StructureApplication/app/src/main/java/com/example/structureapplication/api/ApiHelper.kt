package com.example.structureapplication.api

import com.example.structureapplication.model.PosmRequestApiData
import com.example.structureapplication.model.UserResponse
import retrofit2.Response


interface ApiHelper {

    suspend fun getUser(id: Int): Response<UserResponse>

    suspend fun getUserList(): Response<List<UserResponse>>

    suspend fun getPosmStorageRequestList(accessToken:String): Response<PosmRequestApiData>



}