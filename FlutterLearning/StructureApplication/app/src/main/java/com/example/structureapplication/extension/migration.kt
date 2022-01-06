package com.example.structureapplication.extension

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

var MIGRATION_2_3: Migration = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE movie ADD COLUMN phone TEXT NOT NULL DEFAULT ''")
    }
}