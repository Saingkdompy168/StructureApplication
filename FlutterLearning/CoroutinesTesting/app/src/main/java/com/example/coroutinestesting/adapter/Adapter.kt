package com.example.coroutinestesting.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinestesting.databinding.DateItemBinding
import com.example.coroutinestesting.databinding.GeneralItemBinding
import com.example.coroutinestesting.model.DateItem
import com.example.coroutinestesting.model.GeneralItem
import com.example.coroutinestesting.model.ListItem

class Adapter(
    private val items: List<ListItem>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ListItem.TYPE_DATE ->
                DateViewHolder(DateItemBinding.inflate(layoutInflater))
            else ->
                GeneralViewHolder(GeneralItemBinding.inflate(layoutInflater))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            ListItem.TYPE_DATE -> (holder as DateViewHolder).bind(
                item = items[position] as DateItem,
            )
            ListItem.TYPE_GENERAL -> (holder as GeneralViewHolder).bind(
                item = items[position] as GeneralItem
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].type
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class DateViewHolder(private val binding: DateItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DateItem) {
            binding.txtDate.text = item.date
        }
    }

    inner class GeneralViewHolder(private val binding: GeneralItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GeneralItem) {
            binding.txtTitle.text = item.name
        }
    }

}