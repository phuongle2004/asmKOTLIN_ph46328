
package com.kotlin.asmkotlin_ph46328

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class Cart : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CartScreen()
        }
    }
}

@Composable
fun  CartScreen() {
    Surface {
        Text(text = "giỏ hang ")
    }
}

@Preview
@Composable
fun PreviewCartScreen() {
    CartScreen()
}

