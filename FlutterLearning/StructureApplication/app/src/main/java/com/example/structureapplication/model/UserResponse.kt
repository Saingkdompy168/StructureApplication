package com.example.structureapplication.model

import com.example.structureapplication.adapter.ItemAdapter
import com.example.structureapplication.localroom.model.MovieEntity

class UserResponse : ItemAdapter {
    var name = ""
    var email = ""
    var phone = ""
    fun mapModel(): MovieEntity {
        return MovieEntity().apply {
            this.email = this@UserResponse.email
            this.name = this@UserResponse.name
            this.phone = this@UserResponse.phone
        }
    }
}