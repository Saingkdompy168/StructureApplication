package com.example.structureapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.structureapplication.model.UserResponse
import com.example.structureapplication.repository.UserRepository
import com.example.structureapplication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private var userRepository: UserRepository
) : ViewModel() {
    val userResponse = MutableLiveData<Resource<UserResponse>>()

//    val userResponse: LiveData<Resource<UserResponse>>
//        get() = _res

//    init {
//        getUser()
//    }

    fun getUser(id: Int) = viewModelScope.launch {
        userResponse.postValue(Resource.Loading())
        userRepository.getUser(id).let {
            if (it.isSuccessful) {
                it.body()?.let { resultResponse ->
                    userResponse.postValue(Resource.Success(resultResponse))
                }
            } else {
                userResponse.postValue(Resource.Error(it.errorBody().toString(), null))
            }
        }
    }
}