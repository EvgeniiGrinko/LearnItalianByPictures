package com.example.learnitalianbypictures

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.learnitalianbypictures.databinding.ActivitySignInSignUpBinding
import java.nio.charset.Charset


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
    fun loginUser(context: Context, login: String, password: String) {
        val url: String = "https://b058-82-215-122-139.eu.ngrok.io/api/user/login/"
        val queue = Volley.newRequestQueue(context)
        val requestBody = "login=$login&password=$password"

        val postRequest: StringRequest = object : StringRequest(
            Method.POST, url,
            { response -> // response
                Log.d("Response", response)
            },
            { error ->
                Log.d("Error.Response", error.toString())
            }
        ) {
            override fun getBody(): ByteArray {
                return requestBody.toByteArray(Charset.defaultCharset())
            }
        }
        queue.add(postRequest)
    }
    fun registerUser(context: Context, login: String, password: String, name: String, surname: String) {
        val url: String = "https://b058-82-215-122-139.eu.ngrok.io/api/user/register/"
        val queue = Volley.newRequestQueue(context)
        val requestBody = "login=$login&password=$password&name=$name&surname=$surname"

        val postRequest: StringRequest = object : StringRequest(
            Method.POST, url,
            { response -> // response
                Log.d("Response", response)
            },
            { error ->
                Log.d("Error.Response", error.toString())
            }
        ) {
            override fun getBody(): ByteArray {
                return requestBody.toByteArray(Charset.defaultCharset())
            }
        }
        queue.add(postRequest)
    }



    fun onClickBack(view: View) {
        editName = findViewById(R.id.editTextPersonName)
        editSurname = findViewById(R.id.editTextPersonSurname)
        editLogin = findViewById(R.id.editTextPersonLogin)
        editPassword = findViewById(R.id.editTextPersonPassword)
        val text: String = editLogin.text.toString().trim()
        val text2: String = editPassword.text.toString().trim()
        val text3: String = editName.text.toString().trim()
        val text4: String = editSurname.text.toString().trim()
        if (code == 111) {

            if (TextUtils.isEmpty(text)) {
                bindingClass.editTextPersonLogin.error = "Please Enter a Login!"
            } else if(TextUtils.isEmpty(text2)){
                bindingClass.editTextPersonPassword.error = "Please Enter a Password!"
            } else {
                var result = loginUser(this,text, text2)
                intent.putExtra(
                    "sign_in", arrayOf( text, text2, result.toString())
                )
                setResult(111, intent)
                finish()
            }
        } else if (code == 222) {

            if (TextUtils.isEmpty(text3)) {
                bindingClass.editTextPersonName.error = "Please Enter a Name!"
            } else if(TextUtils.isEmpty(text4)){
                bindingClass.editTextPersonSurname.error = "Please Enter a Surname!"
            } else if(TextUtils.isEmpty(text)){
                bindingClass.editTextPersonLogin.error = "Please Enter a Login!"
            } else if(TextUtils.isEmpty(text2)){
                bindingClass.editTextPersonPassword.error = "Please Enter a Password!"
            } else {
                var result = registerUser(this,text, text2, text3,text4)
                intent.putExtra(
                    "sign_up", arrayOf( text3, text4, text, text2, result.toString())
                )
                setResult(222, intent)
                finish()
            }



        }
    }
}