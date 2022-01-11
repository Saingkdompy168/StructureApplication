package com.example.structureapplication.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.structureapplication.databinding.FragmentDashBoardBinding
import com.example.structureapplication.model.UserResponse
import com.example.structureapplication.util.Resource
import com.example.structureapplication.viewmodel.UserViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashBoardFragment :
    BaseBindingFragment<FragmentDashBoardBinding>(FragmentDashBoardBinding::inflate) {

    private val userViewModel: UserViewModel by viewModels()
    private var count = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel.getUser(2)

//        userViewModel.userResponse.observe(viewLifecycleOwner, {
////            Log.d("serverData", Gson().toJson(it.data?.name))
//            binding.name.text = it.data?.name
//        })
//        binding.textCount.text = count.toString()
//        binding.buttonCount.setOnClickListener {
//            count++
//            binding.textCount.text = count.toString()
//        }

        val listData = listOf(
            UserResponse().apply {
                this.name = "daro"
                this.email = "darkfjdkfjd"
            },
            UserResponse().apply {
                this.name = "dara"
                this.email = "darkfjdkfjd"
            }).sortedBy(UserResponse::name)

        Log.d("dsfdsfsdfsd", Gson().toJson(listData))
        binding.textCount.text = userViewModel.countState.value.toString()

        binding.buttonCount.setOnClickListener {
            userViewModel.incrementCount()
            lifecycleScope.launch {
                userViewModel.countState.collect {
                    binding.textCount.text = it.toString()
                }
            }


        }

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

        executeShellCommand("root")
    }

    private fun executeShellCommand(name: String) {
        var process: Process? = null
        try {
            process = Runtime.getRuntime().exec(name)
            binding.root.text = "It is rooted device"
            Toast.makeText(context, "It is rooted device", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            binding.root.text = "It is not rooted device"
        } finally {
            if (process != null) {
                try {
                    process.destroy()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}
