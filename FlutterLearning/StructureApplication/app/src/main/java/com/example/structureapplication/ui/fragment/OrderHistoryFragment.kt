package com.example.structureapplication.ui.fragment

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.structureapplication.R
import com.example.structureapplication.adapter.BaseRecyclerAdapter
import com.example.structureapplication.adapter.OrderHistoryAdapter
import com.example.structureapplication.databinding.FragmentOrderHistoryBinding
import com.example.structureapplication.model.PosmRequestListModel
import com.example.structureapplication.util.Resource
import com.example.structureapplication.util.SpaceItemDecoration
import com.example.structureapplication.viewmodel.UserListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderHistoryFragment :
    BaseFragment<FragmentOrderHistoryBinding, PosmRequestListModel>() {

    private val userViewModel: UserListViewModel by viewModels()

    override fun layoutManager(): RecyclerView.LayoutManager = LinearLayoutManager(requireContext())

    override fun adapter(): BaseRecyclerAdapter<*, PosmRequestListModel> {
        return OrderHistoryAdapter(mData)
    }

    override fun getIdRecyclerView(): RecyclerView = binding.orderList

    override fun getLayoutResourceId(): Int = R.layout.fragment_order_history

    override fun itemDecoration(): RecyclerView.ItemDecoration {
        return SpaceItemDecoration(
            itemDecorationSize()
        )
    }

    private fun getOrderHistory() {
        userViewModel.getOrderHistory(
            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjE0LCJkZXZpY2VJZCI6NDE1LCJsb2dpbkRhdGUiOiIyMDIyLTAxLTI2VDA4OjE1OjE4LjY0NloifQ.BuOJEsdsYwpZTJjDzJhHjA763pEyneXI3TyntQNC8qE",
            30, offset
        )
    }

    override fun initView() {
        super.initView()
        getOrderHistory()
    }

    override fun loadMore() {
        super.loadMore()
        getOrderHistory()
    }

    override fun initEventListener() {
        super.initEventListener()
        userViewModel.orderResponse.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    onResponseDataSuccess(it.data?.data!!)
                    if (offset == 0) {
                        mData.clear()
                    }
                    mData.addAll(it.data?.data!!)
//                    mAdapter?.notifyItemInserted(it.data.data?.size!!)
                    mAdapter?.notifyDataSetChanged()
                    offset += mData.size
                    Log.d("mdata", mData.size.toString())
                }
                is Resource.Error -> {
                    Log.d("errror", it.message.toString())
                }
                is Resource.Loading -> {

                }
            }
        })
    }


}