package com.example.structureapplication.viewholder

import android.util.Log
import com.example.structureapplication.adapter.BaseRecyclerAdapter
import com.example.structureapplication.databinding.ItemTestingBinding
import com.example.structureapplication.model.PosmRequestListModel

class OrderHistoryViewHolder(binding: ItemTestingBinding) :
    BaseViewHolder<ItemTestingBinding, PosmRequestListModel>(binding) {
    override fun bind(
        binding: ItemTestingBinding,
        adapter: BaseRecyclerAdapter<*, PosmRequestListModel>
    ) {
        val dataModel = adapter.getItem(adapterPosition)
        binding.name.text = dataModel.code
    }
}