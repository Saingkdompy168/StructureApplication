package com.example.structureapplication.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.structureapplication.adapter.BaseRecyclerAdapter
import com.google.gson.Gson

abstract class RefactorViewHolder<BINDING : ViewDataBinding>(var binder: BINDING) :
    RecyclerView.ViewHolder(binder.root) {
    protected val mGson = Gson()
    open fun isDefaultClick(): Boolean = true

    abstract fun binData(baseRecyclerAdapter: BaseRecyclerAdapter<BINDING, *>)

    open fun onViewRecycler() {
        itemView.setOnClickListener(null)
    }
}

//abstract class BaseViewHolder1<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//    protected val mGson = Gson()
//
//    open fun isDefaultClick(): Boolean = true
//
//
////    abstract fun binData(baseRecyclerAdapter: BaseRecyclerAdapter<T>)
//
//    open fun onViewRecycler() {
//        itemView.setOnClickListener(null)
//    }
//
//}