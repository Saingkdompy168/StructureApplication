package com.example.projectinter.ui.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.chipmong.dms.ui.activities.BaseActivity
import com.example.projectinter.R
import com.example.projectinter.utils.JetPackDataStore

class MainActivity : BaseActivity() {

    private var jetPackDataStore: JetPackDataStore? = null

    override fun isShowHeader(): Boolean = false

    override fun isDarkStatus(): Boolean = false

    override fun layoutContainerRes(): Int = R.layout.activity_main

    override fun initView() {
        super.initView()
        jetPackDataStore = JetPackDataStore(this)
        Toast.makeText(
            this,
            "setting Data Store" + jetPackDataStore?.exampleConterFlow,
            Toast.LENGTH_SHORT
        ).show()
    }


    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
            (context as Activity).finish()
        }
    }
}