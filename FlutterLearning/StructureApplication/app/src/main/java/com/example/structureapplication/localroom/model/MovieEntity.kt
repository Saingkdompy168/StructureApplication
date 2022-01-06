package com.example.structureapplication.localroom.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
class MovieEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null

    @ColumnInfo(name = "name")
    var name = ""

    @ColumnInfo(name = "email")
    var email = ""

    @ColumnInfo(name = "phone")
    var phone = ""
}