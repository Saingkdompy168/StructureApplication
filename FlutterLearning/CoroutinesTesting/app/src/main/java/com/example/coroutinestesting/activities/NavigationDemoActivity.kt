package com.example.coroutinestesting.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.coroutinestesting.R

class NavigationDemoActivity : AppCompatActivity() {

    private lateinit var naController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_demo)
        naController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupActionBarWithNavController(
            this,
            naController
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(naController, null)
    }
}