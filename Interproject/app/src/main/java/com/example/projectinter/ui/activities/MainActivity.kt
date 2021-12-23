package com.example.projectinter.ui.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.chipmong.dms.adapter.BaseRecyclerAdapter
import com.chipmong.dms.ui.activities.BaseListActivity
import com.example.projectinter.R
import com.example.projectinter.adapter.UnSelectedAdapter
import com.example.projectinter.models.UnSelectedModel
import com.example.projectinter.utils.JetPackDataStore

class MainActivity : BaseListActivity<UnSelectedModel>() {

    private var jetPackDataStore: JetPackDataStore? = null

    override fun isShowHeader(): Boolean = false

    override fun isDarkStatus(): Boolean = false

    override fun layoutContainerRes(): Int = R.layout.activity_main

    override fun adapter(): BaseRecyclerAdapter<UnSelectedModel> = UnSelectedAdapter(mData)

    override fun initView() {
        super.initView()
        addItem()
        jetPackDataStore = JetPackDataStore(this)
        Toast.makeText(
            this,
            "setting Data Store" + jetPackDataStore?.exampleConterFlow,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun addItem() {
        mData.add(UnSelectedModel().apply {
            title = "Data1"
        })
    }


    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
            (context as Activity).finish()
        }
    }


}