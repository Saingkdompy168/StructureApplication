package com.example.structureapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.structureapplication.extension.toSingleEvent
import com.example.structureapplication.localroom.model.MovieEntity
import com.example.structureapplication.localroom.repository.MovieRepository
import com.example.structureapplication.model.UserResponse
import com.example.structureapplication.repository.UserRepository
import com.example.structureapplication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private var userRepository: UserRepository,
    private var movieRepository: MovieRepository
) : BaseViewModel() {
    private val _userResponse = MutableStateFlow<Resource<UserResponse>>(Resource.Loading())

    val userResponse: StateFlow<Resource<UserResponse>>
        get() = _userResponse

    private val _localData = movieRepository.getAllMovie()

    fun getNotesLiveData(): LiveData<List<MovieEntity>> {
        return _localData.switchMap {
            liveData { emit(it) }.toSingleEvent()
        }
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

    private var _countState = MutableStateFlow(0)
    val countState = _countState.asStateFlow()

    //val countState: StateFlow<Int> get() = _countState
    fun incrementCount() {
        println(countState.value)
        viewModelScope.launch {
            _countState.value += 1
        }
    }


    fun getUser(id: Int) = viewModelScope.launch {
        _userResponse.emit(Resource.Loading())
//        movieRepository.deleteAllMovie()
        userRepository.getUser(id).let {
            if (it.isSuccessful) {
                it.body()?.let { resultResponse ->
                    _userResponse.emit(Resource.Success(resultResponse))
//                    movieRepository.insertMovie(resultResponse.mapModel())
                }
            } else {
                _userResponse.emit(Resource.Error(it.errorBody().toString(), null))
            }
        }
    }
}