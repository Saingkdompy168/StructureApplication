package com.example.structureapplication.localroom.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
class MovieEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var name = ""
    var email = ""
}