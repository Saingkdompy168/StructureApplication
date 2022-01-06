package com.example.structureapplication.localroom.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.structureapplication.localroom.dao.MovieDao
import com.example.structureapplication.localroom.model.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 3,
)
abstract class AppDatabase : RoomDatabase() {
    abstract val dao: MovieDao
}