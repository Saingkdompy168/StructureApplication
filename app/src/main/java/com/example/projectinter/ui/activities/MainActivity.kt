package com.example.projectinter.ui.activities

import com.chipmong.dms.ui.activities.BaseActivity
import com.example.projectinter.R

class MainActivity : BaseActivity() {

    override fun isDarkStatus(): Boolean {
        return false
    }

    override fun layoutContainerRes(): Int =R.layout.activity_main
}