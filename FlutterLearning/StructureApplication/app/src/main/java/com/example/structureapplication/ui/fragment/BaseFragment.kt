package com.example.structureapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<DB : ViewDataBinding> : Fragment(), ViewContract<DB> {
    private var isRegistered = false

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
            onBindData(binding)
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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initActionView()
        onInitLiveData()
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


    override fun onBindData(binding: DB) {

    }

}

interface ViewContract<DB> {
    fun getLayoutResourceId(): Int
    fun onBindData(binding: DB)
}