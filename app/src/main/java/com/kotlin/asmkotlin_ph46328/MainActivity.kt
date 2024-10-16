package com.kotlin.asmkotlin_ph46328

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Nhận thông tin về quyền truy cập
        val isAdmin = intent.getBooleanExtra("IS_ADMIN", false)

        setContent {
            if (isAdmin) {
                AdminMainScreen() // Điều hướng đến màn hình admin
            } else {
                UserMainScreen() // Điều hướng đến màn hình user
            }
        }
    }
}

