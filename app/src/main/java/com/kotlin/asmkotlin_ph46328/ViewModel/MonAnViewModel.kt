package com.kotlin.asmkotlin_ph46328

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kotlin.asmkotlin_ph46328.data.model.MonAn
import com.kotlin.asmkotlin_ph46328.data.repository.MonAnRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class MonAnViewModel(private val repository: MonAnRepository) : ViewModel() {
    private val _monAnList = MutableStateFlow<List<MonAn>>(emptyList())
    val monAnList: StateFlow<List<MonAn>> = _monAnList

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    open val cartItems: StateFlow<List<CartItem>> get() = _cartItems

    // Fetch món ăn từ repository
    fun fetchMonAn() {
        viewModelScope.launch {
            try {
                val fetchedMonAnList = repository.getMonAn() // Gọi API từ repository
                _monAnList.value = fetchedMonAnList
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Thêm món ăn vào danh sách
    fun addMonAn(monAn: MonAn) {
        _monAnList.value = _monAnList.value + monAn // Thêm món ăn mới vào danh sách
    }

    // Thêm món ăn vào giỏ hàng
    fun addToCart(monAn: MonAn, quantity: Int) {
        val existingItem = _cartItems.value.find { it.monAn.id == monAn.id }
        val updatedCartItems = _cartItems.value.toMutableList()

        if (existingItem != null) {
            val updatedItem = existingItem.copy(quantity = existingItem.quantity + quantity)
            updatedCartItems[updatedCartItems.indexOf(existingItem)] = updatedItem
        } else {
            updatedCartItems.add(CartItem(monAn, quantity))
        }

        _cartItems.value = updatedCartItems

        // Kiểm tra giỏ hàng qua Log
        Log.d("CartItems", "Đã thêm sản phẩm vào giỏ hàng: $updatedCartItems")
    }

    // Tính tổng giá tiền
    fun getTotalPrice(): Int {
        return _cartItems.value.sumOf { it.monAn.price * it.quantity }
    }
}





data class CartItem(val monAn: MonAn, var quantity: Int)

class MonAnViewModelFactory(private val repository: MonAnRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MonAnViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MonAnViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
