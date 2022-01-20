package com.example.structureapplication.factory

import androidx.lifecycle.SavedStateHandle
import com.example.structureapplication.extension.ViewModelFactory
import com.example.structureapplication.localroom.repository.MovieRepository
import com.example.structureapplication.repository.UserRepository
import com.example.structureapplication.viewmodel.UserViewModel
import javax.inject.Inject

class UserFactory @Inject constructor(
    private val userRepository: UserRepository,
    private val movieRepository: MovieRepository
) : ViewModelFactory<UserViewModel> {

    override fun create(handle: SavedStateHandle): UserViewModel {
        return UserViewModel(
            userRepository,
            movieRepository,
        )
    }
}