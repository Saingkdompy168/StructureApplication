package com.example.structureapplication.repository

import com.example.structureapplication.api.ApiHelper
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    suspend fun getUser(id: Int) = apiHelper.getUser(id)
}