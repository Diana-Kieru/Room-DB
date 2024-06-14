import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdb.data.User
import com.example.roomdb.data.UserDatabase
import com.example.roomdb.data.UserRepository
import com.example.roomdb.models.LoginRequest
import com.example.roomdb.models.LoginResponse
import com.example.roomdb.utils.SharedPrefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val readAllData: LiveData<List<User>>
    private val repository: UserRepository
    private val sharedPrefs = SharedPrefs(application)


    // LiveData to hold login result
    val loginResult = MutableLiveData<Boolean>()

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

    fun loginUser(email: String, password: String) {
        val loginRequest = LoginRequest(email, password)
        val apiService = RestClient.getApiService(getApplication<Application>())
        val call = apiService.loginUser(loginRequest)

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    // Login successful
                    val token = response.body()?.access_token
                    sharedPrefs.saveToken(token!!)
                    loginResult.postValue(true)
                } else {
                    // Login failed
                    val errorMessage = when (response.code()) {
                        401 -> "Invalid credentials"
                        500 -> "Internal Server Error"
                        else -> response.message()
                    }
                    showToastMessage(errorMessage)
                    loginResult.postValue(false)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                // Network error
                showToastMessage("Network Error: ${t.message}")
                loginResult.postValue(false)
            }
        })
    }



private fun showToastMessage(message: String) {
    Toast.makeText(getApplication(), message, Toast.LENGTH_SHORT).show()
}
}