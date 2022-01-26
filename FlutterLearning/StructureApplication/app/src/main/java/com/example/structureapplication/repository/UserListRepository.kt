package com.example.structureapplication.repository

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

    suspend fun getOrderHistoryList(
        accessToken: String,
        limit: Int = 1,
        offset: Int = 0,
        orderStatus: String? = null,
        outletId: Int? = null,
    ) = apiService.getOrderHistoryList(accessToken, limit, offset, orderStatus, outletId)
}