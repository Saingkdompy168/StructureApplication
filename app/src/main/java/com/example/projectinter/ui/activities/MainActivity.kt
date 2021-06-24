package com.example.projectinter.ui.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.chipmong.dms.ui.activities.BaseActivity
import com.example.projectinter.R

class MainActivity : BaseActivity() {
    override fun isShowHeader(): Boolean =false

    override fun isDarkStatus(): Boolean = false

    override fun layoutContainerRes(): Int = R.layout.activity_main

    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
            (context as Activity).finish()
        }
    }
}