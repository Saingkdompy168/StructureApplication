package com.example.structureapplication.api

import com.example.structureapplication.model.PosmRequestApiData
import com.example.structureapplication.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): Response<UserResponse>

    @GET("users")
    suspend fun getUserList(): Response<List<UserResponse>>


    @GET("v1/storages/requests")
    suspend fun getPosmStorageRequestList(
        @Header("Authorization") accessToken: String,
    ): Response<PosmRequestApiData>

    @GET("v1/sales/orders")
    suspend fun getOrderHistoryList(
        @Header("Authorization") accessToken: String,
        @Query("limit") limit: Int = 100,
        @Query("offset") offset: Int = 0,
        @Query("orderStatus") status: String? = null,
        @Query("outletId") outletId: Int? = null,
        @Query("types") types: String? = "ORDER|REQUEST",
        @Query("notInRequestMethods") notInRequestMethods: String? = "POSM"

    ): Response<PosmRequestApiData>

}