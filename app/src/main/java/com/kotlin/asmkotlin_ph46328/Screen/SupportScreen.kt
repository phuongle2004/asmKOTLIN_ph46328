package com.kotlin.asmkotlin_ph46328

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class Support : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SupportScreen()
        }
    }
}

@Composable
fun SupportScreen() {
    MaterialTheme {
        Surface {
            // Nội dung cho Admin
            Text(text = "Welcome to support")
        }
    }
}

@Preview
@Composable
fun PreviewSupportApp() {
    SupportScreen()
}
