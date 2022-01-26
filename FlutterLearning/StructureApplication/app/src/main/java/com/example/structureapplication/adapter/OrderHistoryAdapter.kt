package com.example.structureapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.structureapplication.R
import com.example.structureapplication.databinding.ItemTestingBinding
import com.example.structureapplication.model.PosmRequestListModel
import com.example.structureapplication.viewholder.BaseViewHolder
import com.example.structureapplication.viewholder.OrderHistoryViewHolder

class OrderHistoryAdapter(mData: List<PosmRequestListModel>) :
    BaseRecyclerAdapter<ItemTestingBinding, PosmRequestListModel>(mData) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ItemTestingBinding, PosmRequestListModel> {
        val binder = DataBindingUtil.inflate<ItemTestingBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_testing,
            parent,
            false
        )
        return OrderHistoryViewHolder(binder)
    }

    override fun shouldLoadMore(): Boolean = true
}