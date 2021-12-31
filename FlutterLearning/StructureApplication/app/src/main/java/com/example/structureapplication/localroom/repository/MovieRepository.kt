package com.example.structureapplication.localroom.repository

import com.example.structureapplication.localroom.dao.MovieDao
import com.example.structureapplication.localroom.model.MovieEntity
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private var movieDao: MovieDao
) {
    fun getAllMovie() = movieDao.getMovie()

    suspend fun deleteAllMovie() = movieDao.deleteAllMovie()

    suspend fun insertMovie(movieEntity: MovieEntity) = movieDao.insertMovie(movieEntity)
}