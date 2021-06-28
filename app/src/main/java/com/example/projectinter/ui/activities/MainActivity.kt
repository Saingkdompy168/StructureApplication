package com.example.projectinter.ui.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.lifecycle.lifecycleScope
import com.chipmong.dms.ui.activities.BaseActivity
import com.chipmong.dms.utils.InternetDetectAsync
import com.example.projectinter.R
import com.example.projectinter.extensions.showErrorDialog
import com.example.projectinter.utils.JetPackDataStore
import kotlinx.coroutines.launch

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