package com.example.structureapplication.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.structureapplication.databinding.FragmentDashBoardBinding
import com.example.structureapplication.util.Resource
import com.example.structureapplication.viewmodel.UserViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class DashBoardFragment :
    BaseBindingFragment<FragmentDashBoardBinding>(FragmentDashBoardBinding::inflate) {

    private val userViewModel: UserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel.getUser(2)

//        userViewModel.userResponse.observe(viewLifecycleOwner, {
////            Log.d("serverData", Gson().toJson(it.data?.name))
//            binding.name.text = it.data?.name
//        })

        lifecycleScope.launchWhenStarted {
            userViewModel.userResponse.collect {
                when (it) {
                    is Resource.Success -> {
                        binding.name.text = it.data?.name
                    }
                    is Resource.Error -> {
                        Snackbar.make(
                            binding.root,
                            it.message.toString(),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    is Resource.Loading -> {

                    }
                }
            }
        }
        userViewModel.getNotesLiveData().observe(viewLifecycleOwner, {
            binding.layoutDraw.removeAllViews()
            it.run {
                for (item in it) {
                    val lparams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    val tv = TextView(requireContext())
                    tv.layoutParams = lparams
                    tv.text = item.email + " - " + item.phone
                    binding.layoutDraw.addView(tv)
                }
                Log.d("localData", Gson().toJson(it))
            }

        })
    }
}