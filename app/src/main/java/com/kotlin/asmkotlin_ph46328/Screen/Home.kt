package com.kotlin.asmkotlin_ph46328

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.kotlin.asmkotlin_ph46328.data.model.MonAn
import com.kotlin.asmkotlin_ph46328.data.repository.MonAnRepository

@Composable
fun HomeScreen(navController: NavHostController, repository: MonAnRepository) {
    val viewModel: MonAnViewModel = viewModel(factory = MonAnViewModelFactory(repository))
    val monAnList by viewModel.monAnList.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    var selectedMonAn by remember { mutableStateOf<MonAn?>(null) } // Biến lưu món ăn được chọn
    var showDialog by remember { mutableStateOf(false) } // Biến điều khiển hiển thị dialog

    // Gọi hàm fetchMonAn khi màn hình được hiển thị
    LaunchedEffect(Unit) {
        viewModel.fetchMonAn()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        HeaderSection()
        SearchBar(searchQuery) { query -> searchQuery = query }
        HorizontalImageScroll()
        FoodCategorySection()

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(monAnList.filter { it.name.contains(searchQuery, ignoreCase = true) }) { monAn ->
                FoodItem(monAn) {
                    selectedMonAn = monAn // Gán món ăn đã chọn
                    showDialog = true // Hiện dialog
                }
            }
        }
    }

    // Hiện dialog nếu showDialog là true
    if (showDialog && selectedMonAn != null) {
        ProductDetailDialog(
            monAn = selectedMonAn!!,
            onDismiss = { showDialog = false },
            onAddToCart = { quantity ->
                viewModel.addToCart(selectedMonAn!!, quantity)
                showDialog = false
                navController.navigate("cart") // Điều hướng đến CartScreen
            }
        )
    }

}


@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(13.dp),
    ) {
        // Hình ảnh ở đầu
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier.size(70.dp)
        )
        Text(
            text = "Cưm Tứm Đim",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 7.dp, top = 16.dp) // Giảm padding giữa logo và chữ
        )
    }
}

@Composable
fun SearchBar(query: String, onQueryChange: (String) -> Unit) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = { Text("Tìm món ăn...") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 0.dp)
    )
}

@Composable
fun HorizontalImageScroll() {
    val scrollState = rememberScrollState()

    // Danh sách hình ảnh (Thay thế bằng ID hình ảnh thực tế của bạn)
    val images = listOf(R.drawable.image1, R.drawable.image1, R.drawable.image3)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState)
            .padding(10.dp)
    ) {
        for (image in images) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .width(200.dp)
                    .wrapContentHeight() // Kích thước hình ảnh
                    .padding(end = 10.dp)
            )
        }
    }
}

@Composable
fun FoodCategorySection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CategoryText("Món ăn")
        CategoryText("Đồ ăn thêm")
        CategoryText("Topping")
        CategoryText("Khác")
    }
}

@Composable
fun CategoryText(text: String) {
    Text(
        color = Color.White,
        text = text,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(10.dp)) // Bo góc
            .shadow(4.dp) // Đổ bóng
            .background(Color.DarkGray) // Nền màu trắng
            .padding(8.dp) // Padding bên trong
    )
}



@Composable
fun ProductDetailDialog(
    monAn: MonAn,
    onDismiss: () -> Unit,
    onAddToCart: (Int) -> Unit // Tham số thêm vào
) {
    val description = "Đây là một món ăn đặc sản, được chế biến từ nguyên liệu tươi ngon và hương vị độc đáo."
    var quantity by remember { mutableStateOf(1) } // Biến lưu số lượng sản phẩm

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = monAn.name, style = MaterialTheme.typography.headlineLarge, textAlign = TextAlign.Center) },
        text = {
            Column {
                Text(text = description, style = MaterialTheme.typography.bodyMedium)
                Image(
                    painter = rememberImagePainter(monAn.thumbnail),
                    contentDescription = null,
                    modifier = Modifier.size(350.dp)
                )
                Text(text = "Giá tiền: ${monAn.price} VND", style = MaterialTheme.typography.titleMedium)

                Spacer(modifier = Modifier.height(8.dp)) // Khoảng cách giữa mô tả và nút
                // Nút tăng/giảm số lượng
                QuantitySelector(quantity = quantity, onQuantityChange = { newQuantity -> quantity = newQuantity })
            }
        },
        confirmButton = {
            Button(onClick = {
                onAddToCart(quantity) // Gọi hàm onAddToCart khi nhấn nút
                onDismiss() // Đóng dialog
            }) {
                Text("Thêm vào giỏ hàng")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Đóng")
            }
        }
    )
}



@Composable
fun QuantitySelector(quantity: Int, onQuantityChange: (Int) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(start = 80.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { if (quantity > 1) onQuantityChange(quantity - 1) }) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Giảm")
        }
        Text(text = "$quantity", style = MaterialTheme.typography.bodyLarge)
        IconButton(onClick = { onQuantityChange(quantity + 1) }) {
            Icon(Icons.Default.ArrowForward, contentDescription = "Tăng")
        }
    }
}



@Composable
fun FoodItem(monAn: MonAn, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() } // Gọi hàm onClick khi nhấn vào món ăn
            .padding(16.dp, 0.dp),



    ) {
        // Hiển thị hình ảnh
        Image(
            painter = rememberImagePainter(monAn.thumbnail),
            contentDescription = null,
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = monAn.name, style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 30.dp))
            Text(text = "${monAn.price} VND", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    val navController = rememberNavController() // Tạo NavController giả
    val repository = MonAnRepository() // Tạo một repository giả nếu cần
    HomeScreen(navController = navController, repository = repository)  // Truyền vào HomeScreen
}
