package com.example.learnitalianbypictures

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.learnitalianbypictures.databinding.ActivitySignInSignUpBinding
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import javax.net.ssl.*

import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call


class SignInSignUpActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivitySignInSignUpBinding
    lateinit var editName: EditText
    lateinit var editSurname: EditText
    lateinit var editLogin: EditText
    lateinit var editPassword: EditText
    var code: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivitySignInSignUpBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        bindingClass.doneLoginButton.setText(R.string.done)
        bindingClass.showAvatarImageButton.setText(R.string.avatar)

        code = intent.getIntExtra("sign", 1)
        if (code == 111) {
            bindingClass.editTextPersonName.visibility = View.GONE
            bindingClass.editTextPersonSurname.visibility = View.GONE
        }
    }


    fun onClickAvatarButton(view: View) {
        bindingClass.avatarImage.visibility = View.VISIBLE

    }

    fun loginUser(login: String, password: String) {
        val apiService = RestApiService()
        val userInfo = UserLoginInfo(
            id = null,
            login = login,
            password = password
        )

        apiService.loginUser(userInfo) {
            val a = it?.id
            Log.d("Fuck user", "User id -  $a")

            if (it?.id != null) {
                val a = it?.id
                // it = newly added user parsed as response
                // it?.id = newly added user ID
                Log.d("Success logging a user", "User  $a")

            } else {
                val b = it?.id
                Log.d("Error logging a user", "User  $b")
            }
        }
    }


    fun registerUser(
        login: String,
        password: String,
        name: String,
        surname: String
    ) {
        val apiService = RestApiService()
        val userInfo = UserRegisterInfo(
            id = null,
            login = login,
            password = password,
            name = name,
            surname = surname
        )

        apiService.registerUser(userInfo) {
            val a = it?.id
            Log.d("Fuck user", "User id -  $a")

            if (it?.id != null) {

                // it = newly added user parsed as response
                // it?.id = newly added user ID
                Log.d("Success registering a user", "User id -  $a")
            } else {
                val b = it?.id
                Log.d("Error registering a user", "User nullllll - $b")
            }
        }

    }


    fun onClickBack(view: View) {
        editName = findViewById(R.id.editTextPersonName)
        editSurname = findViewById(R.id.editTextPersonSurname)
        editLogin = findViewById(R.id.editTextPersonLogin)
        editPassword = findViewById(R.id.editTextPersonPassword)
        val login: String = editLogin.text.toString().trim()
        val password: String = editPassword.text.toString().trim()
        val name: String = editName.text.toString().trim()
        val surname: String = editSurname.text.toString().trim()

        if (code == 111) {
            if (TextUtils.isEmpty(login)) {
                bindingClass.editTextPersonLogin.error = "Please Enter a Login!"
            } else if (TextUtils.isEmpty(password)) {
                bindingClass.editTextPersonPassword.error = "Please Enter a Password!"
            } else {
                var result = loginUser(login, password)
                intent.putExtra(
                    "sign_in", arrayOf(login, password, result.toString())
                )

            }
        } else if (code == 222) {
            if (TextUtils.isEmpty(name)) {
                bindingClass.editTextPersonName.error = "Please Enter a Name!"
            } else if (TextUtils.isEmpty(surname)) {
                bindingClass.editTextPersonSurname.error = "Please Enter a Surname!"
            } else if (TextUtils.isEmpty(login)) {
                bindingClass.editTextPersonLogin.error = "Please Enter a Login!"
            } else if (TextUtils.isEmpty(password)) {
                bindingClass.editTextPersonPassword.error = "Please Enter a Password!"
            } else {
                var registeredUser = UserRegisterInfo(null, login, password, name, surname)
                intent.putExtra(
                    "sign_up", arrayOf(name, surname, login, password, registeredUser)
                )

            }


        }
    }
}

data class UserLoginInfo(
    @SerializedName("id") val id: Int?,
    @SerializedName("login") val login: String,
    @SerializedName("password") val password: String,
)

data class UserRegisterInfo(
    @SerializedName("id") val id: Int?,
    @SerializedName("login") val login: String,
    @SerializedName("password") val password: String,
    @SerializedName("name") val name: String,
    @SerializedName("surname") val surname: String,
)


interface RestApi {

    @Headers("Content-Type: application/json")
    @POST("login")
    fun addUser(@Body userData: UserLoginInfo): Call<UserLoginInfo>

    @Headers("Content-Type: application/json")
    @POST("register")
    fun registerUser(@Body userData: UserRegisterInfo): Call<UserRegisterInfo>
}


object ServiceBuilder {
    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://18.197.110.227/api/user/") // change this IP for testing by your actual machine IP
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}


class RestApiService {
    fun loginUser(userData: UserLoginInfo, onResult: (UserLoginInfo?) -> Unit) {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.addUser(userData).enqueue(
            object : Callback<UserLoginInfo> {
                override fun onFailure(call: Call<UserLoginInfo>, t: Throwable) {
                    val message = t.message
                    val cause = t.cause
                    val tStackTraceString = t.stackTraceToString()
                    Log.d("Fuck user huiddd", "User id - fucked up with message $message caused by $cause and stack trace $tStackTraceString")

                    onResult(null)
                }

                override fun onResponse(
                    call: Call<UserLoginInfo>,
                    response: Response<UserLoginInfo>
                ) {
                    val loggedInUser = response.body()
                    val a = loggedInUser?.id
                    Log.d("Fuck user iddd", "User id -  $a")

                    onResult(loggedInUser)
                }
            }
        )
    }

    fun registerUser(userData: UserRegisterInfo, onResult: (UserRegisterInfo?) -> Unit) {
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.registerUser(userData).enqueue(
            object : Callback<UserRegisterInfo> {
                override fun onFailure(call: Call<UserRegisterInfo>, t: Throwable) {
                    onResult(null)
                }

                override fun onResponse(
                    call: Call<UserRegisterInfo>,
                    response: Response<UserRegisterInfo>
                ) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }
}