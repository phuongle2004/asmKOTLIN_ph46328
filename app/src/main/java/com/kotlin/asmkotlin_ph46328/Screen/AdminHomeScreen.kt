package com.kotlin.asmkotlin_ph46328

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class AdminHome : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AdminHomeScreen()
        }
    }
}

@Composable
fun AdminHomeScreen() {
    MaterialTheme {
        Surface {
            // Nội dung cho Admin
            Text(text = "Welcome to Admin Panel")
        }
    }
}

@Preview
@Composable
fun PreviewMyAdminApp() {
    AdminHomeScreen()
}
