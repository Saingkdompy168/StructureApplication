package com.example.structureapplication.ui.fragment

import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.structureapplication.R
import com.example.structureapplication.adapter.BaseRecyclerAdapter
import com.example.structureapplication.util.InternetBroadcastReceiver

abstract class BaseFragment<DB : ViewDataBinding, T> : Fragment(),
    BaseRecyclerAdapter.OnItemClickListener<T>,
    InternetBroadcastReceiver.OnInternetConnectionChangeListener {

    private var isRegistered = false
    private var intentFilter: IntentFilter? = null
    private var receiver: InternetBroadcastReceiver? = null
    protected var mRecyclerView: RecyclerView? = null
    protected var mData = ArrayList<T>()
    protected var mLayoutManager: RecyclerView.LayoutManager? = null
    protected var mAdapter: BaseRecyclerAdapter<*, T>? = null
    protected var isInit: Boolean = false
    protected var offset = 0
    protected var mItemDecoration: RecyclerView.ItemDecoration? = null

    private var isInternetConnected = true

    abstract fun layoutManager(): RecyclerView.LayoutManager
    abstract fun adapter(): BaseRecyclerAdapter<*, T>
    abstract fun getIdRecyclerView(): RecyclerView

    protected open fun itemDecoration(): RecyclerView.ItemDecoration? {
        return null
    }

    protected open fun itemDecorationSize(): Int {
        return this.resources.getDimensionPixelSize(R.dimen.default_item_decoration_size)
    }

    fun getAdapter(): BaseRecyclerAdapter<*, T>? {
        return mAdapter
    }

    @AnimRes
    protected open fun layoutAnimation(): Int? {
        return null
    }

    protected open fun limit(): Int {
        return 100
    }

    open fun setData(mData: ArrayList<T>) {
        this.mData = mData
    }

    //RecyclerView id must be R.id.recuyclerView
    private fun initRecyclerView() {
//        mRecyclerView = view?.findViewById(R.id.recyclerView)
        mRecyclerView = getIdRecyclerView()
        mLayoutManager = layoutManager()
        mAdapter = adapter()
        mAdapter?.onItemClickListener = this

        mRecyclerView?.apply {
            this.setHasFixedSize(true)
            this.layoutManager = mLayoutManager
            this.adapter = mAdapter

            itemDecoration()?.let {
                mItemDecoration = it
                this.addItemDecoration(it)
            }

            layoutAnimation()?.let {
                this.layoutAnimation = AnimationUtils.loadLayoutAnimation(context, it)
            }
        }
        isInit = true
    }

    open fun initEventListener() {
        mAdapter?.onLoadMoreItems = {
            loadMore()
        }
    }


    protected open fun onResponseDataFailed(t: Throwable) {
        mAdapter?.isLoading = false
    }

    protected open fun loadMore() {

    }

    protected open fun onResponseDataSuccess(data: List<T>) {
        this.offset += data.size
        mAdapter?.apply {
            this.isLoading = false
            this.isLoadAllItemDone = data.isEmpty()
        }
    }


    private var _binding: DB? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (getLayoutResourceId() != 0) {
            _binding =
                DataBindingUtil.inflate(inflater, getLayoutResourceId(), container, false)
            binding.setLifecycleOwner { lifecycle }
            return binding.root
        } else {
            throw IllegalArgumentException("layout resource cannot be null")
        }
    }

    override fun onResume() {
        super.onResume()
        if (!isRegistered) {
//            kotlinBus.register(this)
            isRegistered = true
        }
        requireActivity().registerReceiver(receiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        requireActivity().unregisterReceiver(receiver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initView()
        initActionView()
        onInitLiveData()
        initEventListener()


        intentFilter = IntentFilter()
        intentFilter?.addAction(InternetBroadcastReceiver.CONNECTIVITY_ACTION)
        receiver = InternetBroadcastReceiver()
        receiver?.setOnInternetConnectionChangeListener(this)
    }

    open fun initView() {
        //TODO init view such as adapter, linearLayoutManager
    }

    open fun initActionView() {
        //TODO init action for view such as onClick
    }

    open fun onInitLiveData() {

    }

    override fun onDetach() {
        super.onDetach()
        if (isRegistered) {
            isRegistered = false
        }
    }

    override fun onDisconnected() {
        isInternetConnected = false
        Log.d("internet", "internet not connected")
    }

    override fun onConnected() {
        if (!isInternetConnected) {

            isInternetConnected = true
            Log.d("internet", "internet connected")
        }

    }

    abstract fun getLayoutResourceId(): Int

}

//interface ViewContract<DB> {
//    fun getLayoutResourceId(): Int
//    fun onBindData(binding: DB)
//}