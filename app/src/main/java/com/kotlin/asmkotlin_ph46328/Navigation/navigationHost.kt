package com.kotlin.asmkotlin_ph46328

import SupportScreen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kotlin.asmkotlin_ph46328.CartScreen
import com.kotlin.asmkotlin_ph46328.HistoryScreen
import com.kotlin.asmkotlin_ph46328.HomeScreen
import com.kotlin.asmkotlin_ph46328.ProfileScreen
import com.kotlin.asmkotlin_ph46328.data.repository.MonAnRepository

// Các màn hình của ứng dụng
sealed class Screen(val route: String, val label: String, val icon: @Composable () -> Unit) {
    // Màn hình cho user
    object Home : Screen("home", "Trang chủ", { Icon(Icons.Filled.Home, contentDescription = "Trang chủ") })
    object History : Screen("history", "Lịch sử", { Icon(Icons.Filled.DateRange, contentDescription = "Lịch sử") })
    object Cart : Screen("cart", "Giỏ hàng", { Icon(Icons.Filled.ShoppingCart, contentDescription = "Giỏ hàng") })
    object Profile : Screen("profile", "Hồ sơ", { Icon(Icons.Filled.Person, contentDescription = "Hồ sơ") })

    // Màn hình cho admin
    object AdminHome : Screen("admin_home", "Trang chủ", { Icon(Icons.Filled.Home, contentDescription = "Trang chủ admin") })
    object Management : Screen("management", "Quản lý", { Icon(Icons.Filled.List, contentDescription = "Quản lý") })
    object Support : Screen("support", "Thống kê", { Icon(Icons.Filled.ShoppingCart, contentDescription = "Thống kê") })
    object Statistics : Screen("statistics", "Hỗ trợ", { Icon(Icons.Filled.Call, contentDescription = "Hỗ trợ") })
    object QuanLyLoaiMonAnScreen : Screen("quanly_loaimonan", "", { Icon(Icons.Default.Add, contentDescription = "Cơm tứm Đim") })
    object QuanLyMonAnScreen : Screen("quanly_monan", "", { Icon(Icons.Default.Add, contentDescription = "Cơm tứm Đim") })


}


@Composable
fun AdminMainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController, true) } // Truyền true để xác định là admin
    ) { innerPadding ->
        AdminNavigationHost(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}


@Composable
fun UserMainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController, false) } // Truyền false để xác định là user
    ) { innerPadding ->
        UserNavigationHost(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}




@Composable
fun BottomNavigationBar(navController: NavHostController, isAdmin: Boolean) {
    val items = if (isAdmin) {
        listOf(
            Screen.AdminHome,
            Screen.Management,
            Screen.Support,
            Screen.Statistics

        )
    } else {
        listOf(
            Screen.Home,
            Screen.History,
            Screen.Cart,
            Screen.Profile
        )
    }

    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                label = { Text(screen.label) },
                selected = false,  // Thay đổi logic để xác định trạng thái đã chọn
                onClick = {
                    navController.navigate(screen.route)
                },
                icon = screen.icon
            )
        }
    }
}



@Composable
fun AdminNavigationHost(navController: NavHostController, modifier: Modifier) {
    NavHost(navController, startDestination = Screen.AdminHome.route, modifier = modifier) {
        composable(Screen.AdminHome.route) { AdminHomeScreen() }
        composable(Screen.Management.route) { ManagementScreen(navController) }
        composable(Screen.Support.route) { SupportScreen() }
        composable(Screen.Statistics.route) { StatisticsScreen() }
        composable(Screen.QuanLyLoaiMonAnScreen.route) { QuanLyLoaiMonAnScreen(navController) }

        //mơí thêm
        composable("update_mon_an_screen") { UpdateMonAnScreen(navController) }
        composable(Screen.QuanLyMonAnScreen.route) { QuanLyMonAnScreen(navController) }

    }
}

@Composable
fun UserNavigationHost(navController: NavHostController, modifier: Modifier) {
    val repository = remember { MonAnRepository() } // Khởi tạo repository

    NavHost(navController, startDestination = Screen.Home.route, modifier = modifier) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController, repository = repository)
        }
        composable(Screen.History.route) { HistoryScreen() }
        composable(Screen.Cart.route) { CartScreen() }
        composable(Screen.Profile.route) { ProfileScreen() }
    }
}



