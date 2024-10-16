package com.kotlin.asmkotlin_ph46328.data.repository

import MonAnApiService
import com.kotlin.asmkotlin_ph46328.data.model.MonAn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MonAnRepository {
    // Khởi tạo Retrofit và service
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://comtam.phuocsangbn.workers.dev/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(MonAnApiService::class.java)

    // Hàm gọi API và trả về danh sách món ăn
    suspend fun getMonAn(): List<MonAn> {
        return try {
            service.getMonAn() // Gọi API
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList() // Trả về danh sách rỗng nếu có lỗi
        }
    }
}

