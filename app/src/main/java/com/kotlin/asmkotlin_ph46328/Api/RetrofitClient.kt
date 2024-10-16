package com.kotlin.asmkotlin_ph46328.Api

import com.kotlin.asmkotlin_ph46328.data.model.MonAn
import retrofit2.http.GET
import retrofit2.Call


interface ApiService {
    @GET("foods") // Đường dẫn API cần gọi (thêm vào sau baseUrl)
    fun getMonAn(): Call<List<MonAn>>
}
