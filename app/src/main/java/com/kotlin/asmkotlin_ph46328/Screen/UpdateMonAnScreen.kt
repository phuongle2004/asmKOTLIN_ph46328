package com.kotlin.asmkotlin_ph46328

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun UpdateMonAnScreen(navController: NavHostController) {
    // Danh sách các loại món ăn mẫu
    val loaiMonAnList = listOf("Món Chính", "Món Phụ", "Món Tráng Miệng")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Cập nhật loại món ăn", modifier = Modifier.padding(bottom = 16.dp))

        // Hiển thị danh sách loại món ăn với icon sửa
        loaiMonAnList.forEach { loaiMonAn ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = loaiMonAn)

                // Icon sửa
                IconButton(onClick = {
                    // Xử lý sự kiện khi nhấn vào icon sửa
                    // Ví dụ: có thể mở một dialog hoặc điều hướng đến một màn hình sửa
                }) {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = "Sửa"
                    )
                }
            }
        }

        // Nút quay lại màn hình trước
        Button(
            onClick = { navController.navigateUp() }, // Quay lại màn hình trước đó
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Quay lại")
        }
    }
}