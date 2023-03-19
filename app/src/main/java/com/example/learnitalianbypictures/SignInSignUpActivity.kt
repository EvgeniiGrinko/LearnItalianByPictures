package com.example.learnitalianbypictures

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.learnitalianbypictures.databinding.ActivitySignInSignUpBinding

class SignInSignUpActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivitySignInSignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivitySignInSignUpBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        bindingClass.doneButton.setText(R.string.done)
        bindingClass.showAvatarImageButton.setText(R.string.avatar)

    }

    fun onClickAvatarButton(view: View){
        bindingClass.avatarImage.visibility = View.VISIBLE

    }
    fun onClickBack(view: View){
        intent.putExtra("sign", "ok")
        setResult(RESULT_OK, intent)
        finish()
    }
}