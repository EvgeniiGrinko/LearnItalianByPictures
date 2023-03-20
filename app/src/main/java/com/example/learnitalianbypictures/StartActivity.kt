package com.example.learnitalianbypictures

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.example.learnitalianbypictures.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityStartBinding
    lateinit var editName: String
    lateinit var editSurname: String
    lateinit var editLogin: String
    lateinit var editPassword: String

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
            if (result.resultCode == 222) {
                val it = result.data?.getStringArrayExtra("sign_up")
                editName = it?.get(0).toString()
                editSurname = it?.get(1).toString()
                editLogin = it?.get(2).toString()
                editPassword = it?.get(3).toString()
                bindingClass.welcomingText.text = "Welcome, " + it?.get(2).toString()


            } else if (result.resultCode == 111) {
                val it = result.data?.getStringArrayExtra("sign_in")
                editLogin = it?.get(0).toString()
                editPassword = it?.get(1).toString()
                bindingClass.welcomingText.text = "Welcome, " + it?.get(0).toString()


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