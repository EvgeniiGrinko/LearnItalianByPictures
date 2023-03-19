package com.example.learnitalianbypictures

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.example.learnitalianbypictures.databinding.ActivityMainBinding
import com.example.learnitalianbypictures.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityStartBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
    }

    fun onClickSignUpButton(view: View) {
        val intent = Intent(this, SignInSignUpActivity::class.java)
        intent.putExtra("sign", 222)
        resultLauncher.launch(intent)
    }

    fun onClickSignInButton(view: View) {
        val intent = Intent(this, SignInSignUpActivity::class.java)
        intent.putExtra("sign", 111)

        resultLauncher.launch(intent)
    }


    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == 111) {
                val data: Intent? = result.data

            } else if (result.resultCode == 222) {
                val data: Intent? = result.data

            }
            bindingClass.signInButton.visibility = View.GONE
            bindingClass.signUpButton.visibility = View.GONE
            bindingClass.startButton.visibility = View.VISIBLE

        }

    fun onClickGoToFirstActivity(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}