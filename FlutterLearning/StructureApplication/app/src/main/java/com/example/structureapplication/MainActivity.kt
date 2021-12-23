package com.example.structureapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.structureapplication.databinding.ActivityMainBinding
import com.example.structureapplication.util.Resource
import com.example.structureapplication.util.Status
import com.example.structureapplication.viewmodel.UserListViewModel
import com.example.structureapplication.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //    private val userViewModel: UserViewModel by viewModels()
    private val userViewModel: UserListViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        userViewModel.getUser(3)
        userViewModel.getPosmList("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTA3MywiZGV2aWNlSWQiOjcyOSwibG9naW5EYXRlIjoiMjAyMS0xMi0yM1QwNzoxNTowNi4zODNaIn0.Cupe5a-3QPd-vvTfJbPWMQFqHaGIIGCdVXSj-qwwZIs")

//        userViewModel.userResponseList.observe(this, {
//            when (it) {
//                is Resource.Success -> {
//                    binding.data.text = it.data?.first()?.name
//                }
//                is Resource.Error -> {
//                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
//                }
//                else -> {
//                }
//            }
//        })


        userViewModel.posmData.observe(this, {
            when (it) {
                is Resource.Success -> {
                    binding.data.text = it.data?.data?.first()?.code
                }
                is Resource.Error -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                else -> {
                }
            }
        })

    }
}