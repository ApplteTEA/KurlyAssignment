package com.kurly.assignment.main.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.kurly.assignment.R
import com.kurly.assignment.main.view.screen.MainScreen
import com.kurly.assignment.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    private var lastBackPressedTime = 0L
    private val backPressThreshold = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {
                MainScreen(viewModel)
            }
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastBackPressedTime <= backPressThreshold) {
            finishAffinity() // 앱 완전 종료
        } else {
            lastBackPressedTime = currentTime
            Toast.makeText(this, getString(R.string.app_finish), Toast.LENGTH_SHORT).show()
        }
    }
}