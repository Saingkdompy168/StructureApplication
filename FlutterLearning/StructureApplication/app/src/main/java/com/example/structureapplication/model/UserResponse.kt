package com.example.structureapplication.model

import com.example.structureapplication.localroom.model.MovieEntity

class UserResponse {
    var id = 0
    var name = ""
    var email = ""
    fun mapModel(): MovieEntity {
        return MovieEntity().apply {
            this.email = this@UserResponse.email
            this.name = this@UserResponse.name
        }
    }
}