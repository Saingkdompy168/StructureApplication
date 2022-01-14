package com.example.structureapplication.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BaseViewHolder<BINDING : ViewDataBinding>(var binder: BINDING) :
    RecyclerView.ViewHolder(binder.root) {
}

