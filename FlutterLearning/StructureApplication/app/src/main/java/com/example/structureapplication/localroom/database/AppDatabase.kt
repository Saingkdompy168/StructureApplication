package com.example.structureapplication.localroom.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.structureapplication.localroom.dao.MovieDao
import com.example.structureapplication.localroom.model.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract val dao: MovieDao
}