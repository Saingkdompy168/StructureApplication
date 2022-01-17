package com.example.structureapplication.viewholder

import android.util.Log
import com.example.structureapplication.adapter.BaseRecyclerAdapter
import com.example.structureapplication.adapter.TestingAdapter
import com.example.structureapplication.databinding.ItemTestingBinding

class TestingViewHolder(binding: ItemTestingBinding) : BaseViewHolder<ItemTestingBinding>(binding) {
    override fun bind(binding: ItemTestingBinding, adapter: BaseRecyclerAdapter<*, *>) {
        val model = (adapter as TestingAdapter).getItem(adapterPosition)
//        binding.apply {
//            movie = tempData
//        }

        binding.name.text = model.name
        Log.d("dfsdfsdfsd", model.name)
    }


}