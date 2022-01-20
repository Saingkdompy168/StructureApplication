package com.example.structureapplication.viewholder

import android.util.Log
import com.example.structureapplication.adapter.BaseRecyclerAdapter
import com.example.structureapplication.databinding.ItemTestingBinding
import com.example.structureapplication.model.UserResponse

class TestingViewHolder(binding: ItemTestingBinding) :
    BaseViewHolder<ItemTestingBinding, UserResponse>(binding) {
    override fun bind(binding: ItemTestingBinding, adapter: BaseRecyclerAdapter<*, UserResponse>) {
//        val model = (adapter as TestingAdapter).getItem(adapterPosition)
        val model = adapter.getItem(adapterPosition)
////        binding.apply {
////            movie = tempData
////        }
        binding.name.text = model.name
        Log.d("dfsdfsdfsd", model.name)
    }


}