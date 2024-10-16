package com.kotlin.asmkotlin_ph46328

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class Profile : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileScreen()
        }
    }
}

@Composable
fun ProfileScreen() {
    var name by remember { mutableStateOf("Nguyễn Văn Anh") }
    var phone by remember { mutableStateOf("0342128462") }
    var ward by remember { mutableStateOf("Hoài Đức") }
    var street by remember { mutableStateOf("Vân Canh") }
    var houseNumber by remember { mutableStateOf("422A") }

    var showDialog by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White // Màu nền
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Nút Edit và Signout
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Edit",
                    color = Color.Black,
                    modifier = Modifier
                        .clickable { showDialog = true } // Mở dialog khi nhấn Edit
                )
                Text(
                    text = "Signout",
                    color = Color.Red // Đổi màu cho dễ nhìn
                )
            }

            Spacer(modifier = Modifier.height(16.dp)) // Khoảng cách

            // Ảnh đại diện
            Image(
                painter = painterResource(id = R.drawable.profile_image), // Thay thế bằng ID hình ảnh thực tế
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape) // Bo góc hình ảnh thành hình tròn
            )

            Spacer(modifier = Modifier.height(16.dp)) // Khoảng cách giữa ảnh và tên

            // Thông tin cá nhân với các khung
            ProfileInfo(label = "Họ Tên", value = name)
            ProfileInfo(label = "Số điện thoại", value = phone)
            ProfileInfo(label = "Phường", value = ward)
            ProfileInfo(label = "Đường", value = street)
            ProfileInfo(label = "Số nhà", value = houseNumber)

            // Hiển thị dialog nếu showDialog là true
            if (showDialog) {
                EditProfileDialog(
                    currentName = name,
                    currentPhone = phone,
                    currentWard = ward,
                    currentStreet = street,
                    currentHouseNumber = houseNumber,
                    onDismiss = { showDialog = false },
                    onSave = { newName, newPhone, newWard, newStreet, newHouseNumber ->
                        name = newName
                        phone = newPhone
                        ward = newWard
                        street = newStreet
                        houseNumber = newHouseNumber
                        showDialog = false
                    }
                )
            }
        }
    }
}

@Composable
fun ProfileInfo(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp) // Khoảng cách giữa các trường
            .background(Color.LightGray, shape = RoundedCornerShape(8.dp)) // Khung
            .padding(16.dp) // Padding bên trong khung
    ) {
        Text(text = label, color = Color.Gray) // Nhãn
        Text(text = value, color = Color.Black) // Giá trị
    }
}

@Composable
fun EditProfileDialog(
    currentName: String,
    currentPhone: String,
    currentWard: String,
    currentStreet: String,
    currentHouseNumber: String,
    onDismiss: () -> Unit,
    onSave: (String, String, String, String, String) -> Unit
) {
    var name by remember { mutableStateOf(TextFieldValue(currentName)) }
    var phone by remember { mutableStateOf(TextFieldValue(currentPhone)) }
    var ward by remember { mutableStateOf(TextFieldValue(currentWard)) }
    var street by remember { mutableStateOf(TextFieldValue(currentStreet)) }
    var houseNumber by remember { mutableStateOf(TextFieldValue(currentHouseNumber)) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Sửa hồ sơ") },
        confirmButton = {
            TextButton(onClick = {
                onSave(name.text, phone.text, ward.text, street.text, houseNumber.text)
            }) {
                Text("Lưu")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Hủy")
            }
        },
        text = {
            Column {
                TextField(value = name, onValueChange = { name = it }, label = { Text("Họ Tên") })
                TextField(value = phone, onValueChange = { phone = it }, label = { Text("Số điện thoại") })
                TextField(value = ward, onValueChange = { ward = it }, label = { Text("Phường") })
                TextField(value = street, onValueChange = { street = it }, label = { Text("Đường") })
                TextField(value = houseNumber, onValueChange = { houseNumber = it }, label = { Text("Số nhà") })
            }
        }
    )
}

@Preview
@Composable
fun PreviewProfileScreen() {
    ProfileScreen()
}
