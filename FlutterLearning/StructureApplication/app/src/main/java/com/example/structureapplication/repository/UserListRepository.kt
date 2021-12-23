package com.example.structureapplication.repository

import com.example.structureapplication.api.ApiHelper
import javax.inject.Inject

class UserListRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    suspend fun getUserList() = apiHelper.getUserList()

    suspend fun getPosmStorageRequestList(accessToken: String) =
        apiHelper.getPosmStorageRequestList(
            accessToken
        )
}