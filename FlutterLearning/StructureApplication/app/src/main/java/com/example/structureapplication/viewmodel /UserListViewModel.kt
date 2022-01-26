package com.example.structureapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.structureapplication.model.PosmRequestApiData
import com.example.structureapplication.model.UserResponse
import com.example.structureapplication.repository.UserListRepository
import com.example.structureapplication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private var userListRepository: UserListRepository
) : ViewModel() {

    val userResponseList = MutableLiveData<Resource<List<UserResponse>>>()
    val posmData = MutableLiveData<Resource<PosmRequestApiData>>()
    val orderResponse = MutableLiveData<Resource<PosmRequestApiData>>()

//    val userResponse: LiveData<Resource<UserResponse>>
//        get() = _res

//    init {
//        getUser()
//    }

    fun getUserList() = viewModelScope.launch {
        userResponseList.postValue(Resource.Loading())
        userListRepository.getUserList().let {
            if (it.isSuccessful) {
                it.body()?.let { resultResponse ->
                    userResponseList.postValue(Resource.Success(resultResponse))
                }
            } else {
                userResponseList.postValue(Resource.Error(it.errorBody().toString(), null))
            }
        }
    }

    fun getPosmList(accessToken: String) = viewModelScope.launch {
        posmData.postValue(Resource.Loading())
        userListRepository.getPosmStorageRequestList(accessToken).let {
            if (it.isSuccessful) {
                it.body()?.let { resultResponse ->
                    posmData.postValue(Resource.Success(resultResponse))
                }
            } else {
                posmData.postValue(Resource.Error(it.errorBody().toString(), null))
            }
        }
    }

    fun getOrderHistory(
        accessToken: String, limit: Int = 100,
        offset: Int = 0,
        orderStatus: String? = null,
        outletId: Int? = null
    ) = viewModelScope.launch {
        orderResponse.postValue(Resource.Loading())
        userListRepository.getOrderHistoryList(accessToken, limit, offset, orderStatus, outletId)
            .let {
                if (it.isSuccessful) {
                    it.body()?.let { resultResponse ->
                        orderResponse.postValue(Resource.Success(resultResponse))
                    }
                } else {
                    orderResponse.postValue(Resource.Error(it.errorBody().toString(), null))
                }
            }
    }

}