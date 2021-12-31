package com.example.structureapplication.repository

import com.example.structureapplication.api.ApiService
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiHelper: ApiService
) {
    suspend fun getUser(id: Int) = apiHelper.getUser(id)
}