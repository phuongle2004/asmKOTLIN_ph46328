import com.kotlin.asmkotlin_ph46328.data.model.MonAn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


val retrofit = Retrofit.Builder()
    .baseUrl("https://comtam.phuocsangbn.workers.dev/")
    .addConverterFactory(GsonConverterFactory.create()) // Dùng Gson để parse JSON
    .build()

// Tạo interface cho API
interface MonAnApiService {
    @GET("foods") // Phần đuôi của URL (đã có baseUrl ở trên)
    suspend fun getMonAn(): List<MonAn>
}
