package com.example.projectinter.ui.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.chipmong.dms.ui.activities.BaseActivity
import com.chipmong.dms.utils.InternetDetectAsync
import com.example.projectinter.R
import com.example.projectinter.extensions.showErrorDialog

class MainActivity : BaseActivity() {
    override fun isShowHeader(): Boolean =false

    override fun isDarkStatus(): Boolean = false

    override fun layoutContainerRes(): Int = R.layout.activity_main

    override fun initView() {
        super.initView()
        InternetDetectAsync {
            it?.let {
                hideProgressLoading()
                if (it) {
//                    showErrorDialog("onnection")
                } else {
                    showErrorDialog("please_check_your_connection")
                }

            }
        }.execute()
    }


    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
            (context as Activity).finish()
        }
    }
}