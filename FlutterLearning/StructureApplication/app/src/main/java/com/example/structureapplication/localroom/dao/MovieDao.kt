package com.example.structureapplication.localroom.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.structureapplication.localroom.model.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Query("SELECT * FROM movie")
    fun getMovie(): LiveData<List<MovieEntity>>

    @Query("DELETE FROM movie")
    suspend fun deleteAllMovie()

//    @Query("SELECT * FROM movie")
//    suspend fun getMovieAll(): LiveData<List<MovieEntity>>

}