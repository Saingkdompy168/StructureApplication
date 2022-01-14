package com.example.structureapplication.adapter

import android.util.Log
import com.example.structureapplication.R
import com.example.structureapplication.databinding.ItemTestingBinding
import com.example.structureapplication.model.UserResponse

class TestingAdapter(mData: List<UserResponse>) :
    BaseAdapterBinding<ItemTestingBinding, UserResponse>(mData) {
    override val layoutId: Int
        get() = R.layout.item_testing

    override fun bind(binding: ItemTestingBinding, item: UserResponse) {

        binding.apply {
            movie = item
        }

        Log.d("dfsdfdsfsdf", item.name)
    }
}
