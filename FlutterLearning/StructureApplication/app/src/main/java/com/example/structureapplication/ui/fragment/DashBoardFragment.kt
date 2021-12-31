package com.example.structureapplication.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.structureapplication.R
import com.example.structureapplication.viewmodel.UserViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashBoardFragment : Fragment() {

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        userViewModel.getUser(2)
        val view = inflater.inflate(R.layout.fragment_dash_board, container, false)
        userViewModel.userResponse.observe(viewLifecycleOwner, {
            Log.d("serverData", Gson().toJson(it.data?.name))
        })

        userViewModel.getNotesLiveData().observe(viewLifecycleOwner, {
            it.run {
                this.forEach { localData ->
                    Log.d("dataLocal", localData.name)
                }
            }

        })

        return view

    }
}