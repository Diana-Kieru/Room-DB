import android.content.Context
import com.example.roomdb.network.ApiService
import com.example.roomdb.utils.AuthInterceptor
import com.example.roomdb.utils.SharedPrefs
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RestClient {
    private const val BASE_URL = "https://deploy-run.onrender.com"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private lateinit var sharedPrefs: SharedPrefs
    private lateinit var authInterceptor: AuthInterceptor

    private val client: OkHttpClient
        get() = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

    fun getApiService(context: Context): ApiService {
        sharedPrefs = SharedPrefs(context)
        authInterceptor = AuthInterceptor(sharedPrefs)
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}