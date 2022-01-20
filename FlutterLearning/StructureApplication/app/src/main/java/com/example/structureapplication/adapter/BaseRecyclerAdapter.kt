package com.example.structureapplication.adapter

import android.content.Context
import android.os.Handler
import android.text.format.DateUtils
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.structureapplication.viewholder.BaseViewHolder

abstract class BaseRecyclerAdapter<BINDING : ViewDataBinding, T>(
    mData: List<T>
) : RecyclerView.Adapter<BaseViewHolder<BINDING,T>>(),
    Filterable {

    var onItemClickListener: OnItemClickListener<T>? = null

    protected var mData = ArrayList<T>()
    protected var mDataDisplay: List<T> = ArrayList()


    private var totalItems: Int = 0
    private var lastOrFirstVisibleItem = 0
    var isLoading = false
    var isLoadAllItemDone = false

    /**
     * Load more
     */
    var onLoadMoreItems: (() -> (Unit))? = null
    var onScrolled: ((recyclerView: RecyclerView, dx: Int, dy: Int) -> (Unit))? = null

    var onFilterDone: (() -> (Unit))? = null

    init {
//        this.mData = mData as ArrayList<T>
        this.mDataDisplay = mData
    }

    /**
     * Override should load
     */
    protected open fun shouldLoadMore(): Boolean = false

    protected open fun visibleThreshold(): Int = 5

    protected open fun isLoadMoreAtBottom() = true

    protected open fun limit(): Int = 30

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)


        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                onScrolled?.invoke(recyclerView, dx, dy)
                if (shouldLoadMore()) {
                    if ((recyclerView.layoutManager as LinearLayoutManager).orientation == LinearLayoutManager.VERTICAL) {
                        if (isLoadMoreAtBottom()) {
                            //Load more at top
                            if (dy > 0) {
                                lastOrFirstVisibleItem =
                                    (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                                totalItems = recyclerView.layoutManager?.itemCount ?: 0
                                if (totalItems <= visibleThreshold() + lastOrFirstVisibleItem && !isLoading && !isLoadAllItemDone) {
                                    //More
                                    isLoading = true
                                    onLoadMoreItems?.invoke()
                                }
                            }
                        } else {
                            //Load more at bottom
                            if (dy < 0) {
                                lastOrFirstVisibleItem =
                                    (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                                if (lastOrFirstVisibleItem <= visibleThreshold()) {
                                    //More
                                    isLoading = true
                                    onLoadMoreItems?.invoke()
                                }
                            }

                        }
                    } else {
                        //Scroll Horizontal
                        if (dx > 0) {
                            //Scroll right
                            lastOrFirstVisibleItem =
                                (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                            totalItems = recyclerView.layoutManager?.itemCount ?: 0
                            if (totalItems <= visibleThreshold() + lastOrFirstVisibleItem && !isLoading && !isLoadAllItemDone) {
                                //More
                                isLoading = true
                                onLoadMoreItems?.invoke()
                            }
                        } else {
                            //Scroll left
                            lastOrFirstVisibleItem =
                                (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                            if (lastOrFirstVisibleItem <= visibleThreshold()) {
                                //More
                                isLoading = true
                                onLoadMoreItems?.invoke()
                            }
                        }

                    }
                }

            }
        })

    }

    fun resetLoadingState() {
//        isLoading = false
        isLoadAllItemDone = false
    }


    companion object {
        const val ITEM_HEADER = 0
        const val ITEM_CONTENT = 1
    }

    fun addItemAtFirst(data: T) {
        mData.add(0, data)
        notifyItemChanged(mData.size)
    }

    fun addItem(data: T) {
        mData.add(data)
        notifyItemChanged(mData.size)
    }

    fun setData(mData: List<T>) {
        this.mData = mData as ArrayList<T>
        notifyItemChanged(mData.size)
    }

    fun getItem(): List<T> {
        return mDataDisplay
    }

    open fun getItem(position: Int): T {
        return mDataDisplay[position]
    }

    override fun getItemCount() = mDataDisplay.size


    override fun onBindViewHolder(holder: BaseViewHolder<BINDING,T>, position: Int) {
        if (holder.isDefaultClick()) {
            holder.itemView.setOnClickListener {
                onItemClick(it, holder)
            }
            holder.itemView.setOnLongClickListener {
                onItemClickListener?.onItemLongClick(
                    holder.adapterPosition,
                    getItem(holder.adapterPosition)
                )
                return@setOnLongClickListener true
            }
        }
        holder.bind(holder.binder,this)
    }

    private fun onItemClick(view: View, holder: BaseViewHolder<BINDING,T>) {
        if (holder.adapterPosition != -1) {
            onItemClickListener?.onItemClick(
                holder.itemView,
                holder.adapterPosition,
                getItem(holder.adapterPosition)
            )
            view.isEnabled = false
            //Delay to prevent multiple click
            Handler().postDelayed({
                view.isEnabled = true
            }, DateUtils.SECOND_IN_MILLIS)
        }

    }

    override fun onViewRecycled(holder: BaseViewHolder<BINDING,T>) {
        super.onViewRecycled(holder)
        holder.onViewRecycler()
    }

    interface OnItemClickListener<in T> {
        /**
         * Call when item view click
         * @param v view
         * @param position item position
         * @param data model of position
         */
        fun onItemClick(v: View, position: Int, data: T) {}

        /**
         * Call when item view long click
         * @param position item position
         * @param data model of position
         */
        fun onItemLongClick(position: Int, data: T) {}

        /**
         * Call when other chile view long click
         * @param position item position
         * @param data model of position
         */
        fun onChildViewClick(position: Int, data: T) {}
    }

    open
    /**
     * Single Click
     */
    class RecyclerTouchListener<T>(
        context: Context,
        recycleView: RecyclerView,
        private val mItemClickListener: OnItemClickListener<T>
    ) : RecyclerView.OnItemTouchListener {
        private val mGestureDetector: GestureDetector =
            GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    return true
                }

                override fun onLongPress(e: MotionEvent) {
                    val child = recycleView.findChildViewUnder(e.x, e.y)
                    child?.let {
                        if (recycleView.adapter is BaseRecyclerAdapter<*, *>) {
                            val baseAdapter = recycleView.adapter as BaseRecyclerAdapter<*, *>
                            mItemClickListener.onItemLongClick(
                                recycleView.getChildAdapterPosition(child),
                                baseAdapter.getItem(recycleView.getChildAdapterPosition(child)) as T
                            )
                        }
                    }

                }
            })

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
            val child = rv.findChildViewUnder(e.x, e.y)
            child?.let {
                if (mGestureDetector.onTouchEvent(e) && rv.adapter is BaseRecyclerAdapter<*, *>) {
                    val baseAdapter = rv.adapter as BaseRecyclerAdapter<*, *>
                    mItemClickListener.onItemClick(
                        child,
                        rv.getChildAdapterPosition(child),
                        (baseAdapter.getItem(rv.getChildAdapterPosition(child)) as T?)!!
                    )
                }
            }
            return false
        }

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

        }

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

        }
    }

    /**
     * Override filter to filter data when search
     */
    override fun getFilter(): Filter? {
        return null
    }

    /**
     * All all data from other list and notify adapter
     * @param newData other list
     * @param isClear is true clear old list
     */
    fun addAllAndNotify(newData: List<T>, isClear: Boolean = true) {
        mData.apply {
            if (isClear)
                this.clear()
            this.addAll(newData)
        }
        notifyDataSetChanged()
    }


}