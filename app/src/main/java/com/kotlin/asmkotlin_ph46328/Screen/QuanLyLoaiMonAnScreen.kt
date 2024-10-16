package com.kotlin.asmkotlin_ph46328

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuanLyLoaiMonAnScreen(navController: NavController) {
    val loaiMonAnList = remember { mutableStateListOf("Món Chính", "Món Phụ", "Món Tráng Miệng") }
    var showDialog by remember { mutableStateOf(false) }
    var currentMonAn by remember { mutableStateOf("") }
    var editingIndex by remember { mutableStateOf(-1) }
    var showDeleteDialog by remember { mutableStateOf(false) } // Trạng thái để hiển thị hộp thoại xóa
    var deletingIndex by remember { mutableStateOf(-1) } // Chỉ số món ăn đang được xóa

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black) // Màu nền đen
    ) {
        // TopAppBar với hình ảnh và văn bản
        TopAppBar(
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier.size(60.dp) // Kích thước hình ảnh
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Khoảng cách giữa hình ảnh và văn bản
                    Text(text = "Quản lý Món Ăn", style = MaterialTheme.typography.titleMedium, color = Color.White) // Đổi màu chữ thành trắng
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Black) // Màu nền của TopAppBar
        )
        Spacer(modifier = Modifier.height(18.dp))
        // Nút thêm loại món ăn
        Button(
            onClick = {
                currentMonAn = ""
                editingIndex = -1 // Đặt lại trạng thái khi nhấn nút thêm
                showDialog = true
            },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue) // Màu nút
        ) {
            Text(text = "Thêm loại món ăn", color = Color.White) // Đổi màu chữ nút thành trắng
        }

        // Dialog thêm hoặc chỉnh sửa loại món ăn
        if (showDialog) {
            AddMonAnDialog(
                onDismiss = { showDialog = false },
                onAdd = { loaiMonAn ->
                    if (editingIndex >= 0) {
                        loaiMonAnList[editingIndex] = loaiMonAn // Cập nhật món ăn trong danh sách
                    } else {
                        loaiMonAnList.add(loaiMonAn) // Thêm món ăn mới vào danh sách
                    }
                    showDialog = false // Đóng dialog
                },
                initialMonAn = currentMonAn // Truyền món ăn hiện tại vào dialog
            )
        }

        // Hiển thị danh sách loại món ăn với số thứ tự
        LazyColumn {
            itemsIndexed(loaiMonAnList) { index, monAn ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { // Hiện hộp thoại xác nhận khi nhấn vào tên món ăn
                            deletingIndex = index
                            showDeleteDialog = true
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Hiển thị số thứ tự
                    Text(
                        text = "${index + 1}",
                        modifier = Modifier.width(40.dp), // Độ rộng cố định cho số thứ tự
                        color = Color.White // Đổi màu chữ thành trắng
                    )
                    // Căn giữa tên món ăn
                    Text(
                        text = monAn,
                        modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                            .wrapContentWidth(Alignment.CenterHorizontally), // Căn giữa
                        color = Color.White // Đổi màu chữ thành trắng
                    )
                    IconButton(onClick = {
                        currentMonAn = monAn // Cập nhật món ăn hiện tại để chỉnh sửa
                        editingIndex = index // Cập nhật chỉ số món ăn đang chỉnh sửa
                        showDialog = true // Hiện dialog
                    }) {
                        Icon(Icons.Filled.Edit, contentDescription = "Sửa", tint = Color.White) // Đổi màu icon thành trắng
                    }
                }
            }
        }

        // Hộp thoại xác nhận xóa
        if (showDeleteDialog) {
            DeleteMonAnDialog(
                onDismiss = { showDeleteDialog = false },
                onDelete = {
                    loaiMonAnList.removeAt(deletingIndex) // Xóa món ăn trong danh sách
                    showDeleteDialog = false // Đóng hộp thoại
                },
                monAn = loaiMonAnList[deletingIndex] // Truyền tên món ăn cần xóa vào dialog
            )
        }
    }
}

@Composable
fun AddMonAnDialog(
    onDismiss: () -> Unit,
    onAdd: (String) -> Unit,
    initialMonAn: String // Tham số mới để nhận tên món ăn ban đầu
) {
    var loaiMonAn by remember { mutableStateOf(initialMonAn) } // Sử dụng tham số để khởi tạo giá trị

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Thêm hoặc chỉnh sửa loại món ăn", color = Color.Black) }, // Đổi màu chữ thành đen
        text = {
            TextField(
                value = loaiMonAn,
                onValueChange = { loaiMonAn = it },
                label = { Text("Tên loại món ăn") },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Blue, // Màu chỉ thị khi focus
                    unfocusedIndicatorColor = Color.Gray, // Màu chỉ thị khi không focus
                    focusedLabelColor = Color.Black, // Màu chữ label khi focus
                    unfocusedLabelColor = Color.Black, // Màu chữ label khi không focus
//                    backgroundColor = Color.White // Màu nền của TextField
                )
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    if (loaiMonAn.isNotBlank()) { // Kiểm tra xem tên món ăn không trống
                        onAdd(loaiMonAn) // Truyền loại món ăn mới
                        loaiMonAn = "" // Reset input
                    }
                }
            ) {
                Text("Thêm")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Hủy")
            }
        }
    )
}

@Composable
fun DeleteMonAnDialog(
    onDismiss: () -> Unit,
    onDelete: () -> Unit,
    monAn: String // Tham số để nhận tên món ăn cần xóa
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Xóa món ăn", color = Color.Black) },
        text = { Text("Bạn có chắc chắn muốn xóa món này?  $monAn", color = Color.Black) },
        confirmButton = {
            Button(onClick = {
                onDelete() // Gọi hàm xóa
            }) {
                Text("Có")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Không")
            }
        }
    )
}
