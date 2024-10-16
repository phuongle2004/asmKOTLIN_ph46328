import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

// Màn hình cho người dùng
@Composable
fun HomeScreen() {
    Text(text = "Trang chủ")
}

@Composable
fun HistoryScreen() {
    Text(text = "Lịch sử")
}

@Composable
fun CartScreen() {
    Text(text = "Giỏ hàng")
}

@Composable
fun ProfileScreen() {
    Text(text = "Hồ sơ")
}

// Màn hình cho admin
@Composable
fun AdminHomeScreen() {
    Text(text = "Trang chủ Admin")
}

@Composable
fun StatisticsScreen() {
    Text(text = "Thống kê")
}

@Composable
fun ManagementScreen() {
    Text(text = "Quản lý")
}

@Composable
fun SupportScreen() {
    Text(text = "Hỗ trợ")
}

// Preview cho các màn hình
@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}

@Preview(showBackground = true)
@Composable
fun PreviewAdminHomeScreen() {
    AdminHomeScreen()
}
