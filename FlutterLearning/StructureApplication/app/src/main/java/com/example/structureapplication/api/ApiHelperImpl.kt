package com.example.structureapplication.api

import com.example.structureapplication.model.PosmRequestApiData
import com.example.structureapplication.model.UserResponse
import retrofit2.Response
import javax.inject.Inject

//class ApiHelperImpl @Inject constructor(
//    private val apiService: ApiService
//) : ApiHelper {
//
//    override suspend fun getUser(id: Int): Response<UserResponse> = apiService.getUser(id)
//
//    override suspend fun getUserList(): Response<List<UserResponse>> = apiService.getUserList()
//
//    override suspend fun getPosmStorageRequestList(accessToken: String): Response<PosmRequestApiData> {
//        return apiService.getPosmStorageRequestList(accessToken)
//    }
//
//}