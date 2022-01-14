package com.example.structureapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.structureapplication.viewholder.BaseViewHolder


abstract class BaseAdapterBinding<V : ViewDataBinding, T>(
    var data: List<T>
) : RecyclerView.Adapter<BaseViewHolder<V>>() {

    @get:LayoutRes
    abstract val layoutId: Int

    abstract fun bind(binding: V, item: T)

    fun updateData(list: List<T>) {
        this.data = list
        notifyItemChanged(list.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<V> {
        val binder = DataBindingUtil.inflate<V>(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )

        return BaseViewHolder(binder)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<V>, position: Int) {
        bind(holder.binder, data[position])
    }

    override fun getItemCount(): Int = data.size
}