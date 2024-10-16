//package com.kotlin.asmkotlin_ph46328
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.material.icons.filled.Close
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.text.style.LineHeightStyle
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavHostController
//import coil.compose.rememberImagePainter
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//
//
//class ProductDetailScreen : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            ProductDetailScreen()
//        }
//    }
//}
//
//@Composable
//fun ProductDetailScreen(
//    monAnId: Int,
//    navController: NavHostController,
//    viewModel: MonAnViewModel = remember { MonAnViewModel() }
//) {
//    val monAn = viewModel.getMonAnById(monAnId) // Lấy thông tin món ăn từ ViewModel
//    var quantity by remember { mutableStateOf(1) } // Biến lưu số lượng sản phẩm
//
//    Column(modifier = Modifier.padding(16.dp)) {
//        // Hiển thị hình ảnh và thông tin sản phẩm
//        monAn?.let {
//            Text(text = it.name, style = MaterialTheme.typography.titleLarge)
//            Text(text = "${it.price} VND", style = MaterialTheme.typography.bodyLarge)
//            Image(
//                painter = rememberImagePainter(it.imageUrl),
//                contentDescription = null,
////                modifier = Modifier.size(200.dp)
//            )
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Nút tăng/giảm số lượng
//        Row(
//            modifier = Modifier.fillMaxWidth().padding(16.dp),
//            verticalAlignment = Alignment.CenterVertically // Sửa ở đây
//        ) {
//            IconButton(onClick = { if (quantity > 1) quantity-- }) {
//                Icon(Icons.Default.Close, contentDescription = "Giảm")
//            }
//            Text(text = "$quantity", style = MaterialTheme.typography.bodyLarge)
//            IconButton(onClick = { quantity++ }) {
//                Icon(Icons.Default.Add, contentDescription = "Tăng")
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Nút thêm vào giỏ hàng
//        Button(onClick = {
//            // Thêm sản phẩm và số lượng vào Cart
//            monAn?.let {
//                viewModel.addToCart(it, quantity)
//                // Điều hướng đến màn hình giỏ hàng
//                navController.navigate("cart")
//            }
//        }) {
//            Text(text = "Thêm vào giỏ hàng")
//        }
//    }
//}
