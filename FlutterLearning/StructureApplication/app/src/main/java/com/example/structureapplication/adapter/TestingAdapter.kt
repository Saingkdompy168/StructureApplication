package com.example.structureapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.structureapplication.R
import com.example.structureapplication.databinding.ItemTestingBinding
import com.example.structureapplication.model.UserResponse
import com.example.structureapplication.viewholder.BaseViewHolder
import com.example.structureapplication.viewholder.TestingViewHolder

class TestingAdapter(mData: List<UserResponse>) :
    BaseRecyclerAdapter<ItemTestingBinding, UserResponse>(mData) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ItemTestingBinding> {
        val binder = DataBindingUtil.inflate<ItemTestingBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_testing,
            parent,
            false
        )
        return TestingViewHolder(binder)
    }


//    override fun bind(binding: ItemTestingBinding, item: UserResponse) {
////        binding.apply {
////            movie = item
////        }
//
//        binding.name.text = item.name
//
//        Log.d("dfsdfdsfsdf", item.name)
//    }
}
