package com.example.structureapplication.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecoration(
    private var space: Int = 0,
    private var isGrid: Boolean = false,
    private var isHorizon: Boolean = false,
    private var columnCount: Int? = null
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (!isGrid) {
            if (isHorizon) {
                outRect.right = space
            } else {
                outRect.bottom = space
            }

        } else {
            outRect.right = space / 2
            outRect.left = space / 2
            if (columnCount != null) {
                columnCount?.let {
                    val holder = parent.findContainingViewHolder(view)
                    holder?.let {
                        val position = holder.adapterPosition
                        outRect.top = if (position<columnCount!!) 0 else space / 2
                    }
                }
            } else {
                outRect.top = space / 2
            }

            outRect.bottom = space / 2
        }

    }

}