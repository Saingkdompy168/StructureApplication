package com.example.projectinter.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.chipmong.dms.ui.activities.BaseActivity
import com.example.projectinter.R

class SplashScreenActivity : BaseActivity() {

    override fun isShowHeader(): Boolean {
        return false
    }

    override fun initView() {
        super.initView()
        Handler(Looper.getMainLooper()).postDelayed({
            MainActivity.launch(this)
        }, 1500)
    }

    override fun layoutContainerRes(): Int = R.layout.activity_splash_screen
}