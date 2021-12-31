package com.example.structureapplication.viewmodel

import androidx.lifecycle.*
import com.example.structureapplication.extension.toSingleEvent
import com.example.structureapplication.localroom.model.MovieEntity
import com.example.structureapplication.localroom.repository.MovieRepository
import com.example.structureapplication.model.UserResponse
import com.example.structureapplication.repository.UserRepository
import com.example.structureapplication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.LiveData


@HiltViewModel
class UserViewModel @Inject constructor(
    private var userRepository: UserRepository,
    private var movieRepository: MovieRepository
) : ViewModel() {
    private val _userResponse = MutableLiveData<Resource<UserResponse>>()

    val userResponse = _userResponse.switchMap {
        liveData { emit(it) }
    }.toSingleEvent()

    private val localData = movieRepository.getAllMovie()

    fun getNotesLiveData(): LiveData<List<MovieEntity>> {
        return localData.switchMap {
            liveData { emit(it) }
        }.toSingleEvent()
    }

//    fun getNotesLiveData(): LiveData<List<MovieEntity>> {
//        return localData
//    }

//
//    val userResponse: LiveData<Resource<UserResponse>>
//        get() = _userResponse

//    init {
//        getUser()
//    }

    //    private val _move = movieRepository.getAllMovie()
//    val movies = _move.switchMap { liveData {
//        this.emit(it)
//    } }

//    fun getMovieOffline() = liveData {
//        val artistList = movieRepository.getAllMovie()
//        emit(artistList)
//    }


    fun getUser(id: Int) = viewModelScope.launch {
        _userResponse.postValue(Resource.Loading())
//        movieRepository.deleteAllMovie()
        userRepository.getUser(id).let {
            if (it.isSuccessful) {
                it.body()?.let { resultResponse ->
                    _userResponse.postValue(Resource.Success(resultResponse))
                    movieRepository.insertMovie(resultResponse.mapModel())
                }
            } else {
                _userResponse.postValue(Resource.Error(it.errorBody().toString(), null))
            }
        }
    }
}