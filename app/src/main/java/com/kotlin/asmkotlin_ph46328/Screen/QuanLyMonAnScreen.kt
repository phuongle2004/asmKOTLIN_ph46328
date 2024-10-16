package com.kotlin.asmkotlin_ph46328

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class MonAn(val tenMonAn: String, val loaiMonAn: String, val gia: Int, val hinhAnh: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuanLyMonAnScreen(navController: NavController) {
    val monAnList = remember { mutableStateListOf<MonAn>() }
    val loaiMonAnList = listOf("Món Chính", "Món Phụ", "Món Tráng Miệng")
    var showAddDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }
    var selectedMonAn by remember { mutableStateOf<MonAn?>(null) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var monAnToDelete by remember { mutableStateOf<MonAn?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // TopAppBar với hình ảnh và văn bản
        TopAppBar(
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier.size(60.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Quản lý Món Ăn",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Black)
        )
        Spacer(modifier = Modifier.height(18.dp))

        // Nút thêm loại món ăn
        Button(
            onClick = { showAddDialog = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text(text = "Thêm món ăn", color = Color.White)
        }

        // Hiển thị dialog thêm món ăn
        if (showAddDialog) {
            AddMonAnDialog(
                loaiMonAnList = loaiMonAnList,
                onDismiss = { showAddDialog = false },
                onAdd = { tenMonAn, loaiMon, gia, hinhAnh ->
                    monAnList.add(MonAn(tenMonAn, loaiMon, gia, hinhAnh))
                    showAddDialog = false
                }
            )
        }

        // Hiển thị danh sách món ăn
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            items(monAnList) { monAn ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(8.dp))
                    // Hiển thị thông tin món ăn
                    Column(
                        modifier = Modifier.weight(1f) // Cho phép chiếm diện tích còn lại
                    ) {
                        // Thêm sự kiện nhấn vào tên món ăn
                        Text(
                            text = monAn.tenMonAn,
                            fontSize = 18.sp,
                            color = Color.Black,
                            modifier = Modifier.clickable {
                                monAnToDelete = monAn
                                showDeleteDialog = true
                            }
                        )
                        Text(text = "Loại: ${monAn.loaiMonAn}", color = Color.Gray)
                        Text(text = "${monAn.gia}đ", color = Color.Red)
                    }

                    // Icon chỉnh sửa
                    IconButton(
                        onClick = {
                            selectedMonAn = monAn
                            showEditDialog = true
                        }
                    ) {
                        Icon(Icons.Filled.Edit, contentDescription = "Chỉnh sửa")
                    }
                }
            }
        }

        // Hiển thị dialog chỉnh sửa món ăn
        if (showEditDialog && selectedMonAn != null) {
            EditMonAnDialog(
                monAn = selectedMonAn!!,
                loaiMonAnList = loaiMonAnList,
                onDismiss = { showEditDialog = false; selectedMonAn = null },
                onEdit = { tenMonAn, loaiMon, gia, hinhAnh ->
                    // Cập nhật món ăn trong danh sách
                    val index = monAnList.indexOf(selectedMonAn)
                    if (index != -1) {
                        monAnList[index] = MonAn(tenMonAn, loaiMon, gia, hinhAnh)
                    }
                    showEditDialog = false
                    selectedMonAn = null
                }
            )
        }

        // Hiển thị dialog xác nhận xóa món ăn
        if (showDeleteDialog && monAnToDelete != null) {
            AlertDialog(
                onDismissRequest = { showDeleteDialog = false; monAnToDelete = null },
                title = { Text("Xác nhận xóa") },
                text = { Text("Bạn có chắc chắn muốn xóa món ăn này?") },
                confirmButton = {
                    Button(
                        onClick = {
                            monAnList.remove(monAnToDelete)
                            showDeleteDialog = false
                            monAnToDelete = null
                        }
                    ) {
                        Text("Có")
                    }
                },
                dismissButton = {
                    Button(onClick = { showDeleteDialog = false; monAnToDelete = null }) {
                        Text("Không")
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMonAnDialog(
    loaiMonAnList: List<String>,
    onDismiss: () -> Unit,
    onAdd: (String, String, Int, String) -> Unit
) {
    var loaiMonAn by remember { mutableStateOf(loaiMonAnList.first()) }
    var tenMonAn by remember { mutableStateOf("") }
    var gia by remember { mutableStateOf("") }
    var hinhAnh by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Thêm món ăn") },
        text = {
            Column {
                TextField(
                    value = tenMonAn,
                    onValueChange = { tenMonAn = it },
                    label = { Text("Tên món ăn") },
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = gia,
                    onValueChange = { gia = it },
                    label = { Text("Giá (VND)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

//                TextField(
//                    value = hinhAnh,
//                    onValueChange = { hinhAnh = it },
//                    label = { Text("URL hình ảnh") },
//                    modifier = Modifier.fillMaxWidth()
//                )

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    TextField(
                        value = loaiMonAn,
                        onValueChange = {},
                        label = { Text("Loại món ăn") },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        loaiMonAnList.forEach { loai ->
                            DropdownMenuItem(
                                text = { Text(loai) },
                                onClick = {
                                    loaiMonAn = loai
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (tenMonAn.isNotBlank() && loaiMonAn.isNotBlank() && gia.isNotBlank()) {
                        onAdd(tenMonAn, loaiMonAn, gia.toInt(), hinhAnh)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditMonAnDialog(
    monAn: MonAn,
    loaiMonAnList: List<String>,
    onDismiss: () -> Unit,
    onEdit: (String, String, Int, String) -> Unit
) {
    var loaiMonAn by remember { mutableStateOf(monAn.loaiMonAn) }
    var tenMonAn by remember { mutableStateOf(monAn.tenMonAn) }
    var gia by remember { mutableStateOf(monAn.gia.toString()) }
    var hinhAnh by remember { mutableStateOf(monAn.hinhAnh) }
    var expanded by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Chỉnh sửa món ăn") },
        text = {
            Column {
                TextField(
                    value = tenMonAn,
                    onValueChange = { tenMonAn = it },
                    label = { Text("Tên món ăn") },
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = gia,
                    onValueChange = { gia = it },
                    label = { Text("Giá (VND)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

//                TextField(
//                    value = hinhAnh,
//                    onValueChange = { hinhAnh = it },
//                    label = { Text("URL hình ảnh") },
//                    modifier = Modifier.fillMaxWidth()
//                )

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    TextField(
                        value = loaiMonAn,
                        onValueChange = {},
                        label = { Text("Loại món ăn") },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        loaiMonAnList.forEach { loai ->
                            DropdownMenuItem(
                                text = { Text(loai) },
                                onClick = {
                                    loaiMonAn = loai
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (tenMonAn.isNotBlank() && loaiMonAn.isNotBlank() && gia.isNotBlank()) {
                        onEdit(tenMonAn, loaiMonAn, gia.toInt(), hinhAnh)
                    }
                }
            ) {
                Text("Cập nhật")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Hủy")
            }
        }
    )
}
