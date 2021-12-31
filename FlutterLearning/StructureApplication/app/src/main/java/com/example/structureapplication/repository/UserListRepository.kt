package com.example.structureapplication.repository

import com.example.structureapplication.api.ApiHelper
import com.example.structureapplication.api.ApiService
import javax.inject.Inject

class UserListRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getUserList() = apiService.getUserList()

    suspend fun getPosmStorageRequestList(accessToken: String) =
        apiService.getPosmStorageRequestList(
            accessToken
        )
}