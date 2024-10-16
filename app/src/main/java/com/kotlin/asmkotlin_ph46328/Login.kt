package com.kotlin.asmkotlin_ph46328

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.kotlin.asmkotlin_ph46328.MainActivity

class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApp { username, password ->
                // Kiểm tra đăng nhập
                val isAdmin = checkCredentials(username, password)
                if (isAdmin != null) {
                    // Nếu đăng nhập thành công, điều hướng đến MainScreen
                    val intent = Intent(this, MainActivity::class.java).apply {
                        putExtra("IS_ADMIN", isAdmin) // Truyền thông tin về quyền
                    }
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Authentication Failed.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun checkCredentials(username: String, password: String): Boolean? {
        return when {
            username == "admin" && password == "123" -> true // admin
            username == "user" && password == "456" -> false // user
            else -> null // Thất bại
        }
    }
}


@Composable
fun MyApp(onSignInClick: (String, String) -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Black
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                // Hiển thị ảnh logo
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(350.dp)
                        .padding(top = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Ô nhập Username
                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username") },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(0.8f),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface
                    )
                )

                // Ô nhập Password
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(0.8f),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        focusedTextColor = MaterialTheme.colorScheme.onSurface
                    ),
                    visualTransformation = PasswordVisualTransformation() // Để ẩn mật khẩu khi nhập
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Nút đăng nhập
                Button(
                    onClick = { onSignInClick(username, password) },
                    modifier = Modifier
                        .padding(top = 16.dp)
                ) {
                    Text(text = "Đăng nhập", color = Color.White)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewMyApp() {
    MyApp { _, _ -> }
}
