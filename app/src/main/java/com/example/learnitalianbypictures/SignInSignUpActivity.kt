package com.example.learnitalianbypictures

import android.R.string
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.learnitalianbypictures.databinding.ActivitySignInSignUpBinding


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
                intent.putExtra(
                    "sign_in", arrayOf( text, text2)
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
                intent.putExtra(
                    "sign_up", arrayOf( text3, text4, text, text2)

                )
                setResult(222, intent)
                finish()
            }



        }
    }
}