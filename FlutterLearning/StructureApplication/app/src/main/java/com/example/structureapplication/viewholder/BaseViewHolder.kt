package com.example.structureapplication.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.structureapplication.adapter.BaseRecyclerAdapter
import com.google.gson.Gson

abstract class BaseViewHolder<BINDING : ViewDataBinding,T>(var binder: BINDING) :
    RecyclerView.ViewHolder(binder.root) {

    protected val mGson = Gson()

    open fun isDefaultClick() : Boolean = true

    abstract fun bind(binding: BINDING, adapter: BaseRecyclerAdapter<*, T>)

    open fun onViewRecycler() {
        itemView.setOnClickListener(null)
    }
}

