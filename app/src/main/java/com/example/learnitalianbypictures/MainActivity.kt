package com.example.learnitalianbypictures

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.learnitalianbypictures.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        bindingClass.FirstOption.setOnClickListener{
            bindingClass.Result.visibility = View.VISIBLE
            bindingClass.Result.text = "Your answer isn't correct :( Try again!"
        }
        bindingClass.SecondOption.setOnClickListener{
            bindingClass.Result.visibility = View.VISIBLE
            bindingClass.Result.text = "Your answer isn't correct :( Try again!"
        }
        bindingClass.ThirdOption.setOnClickListener{
            bindingClass.Result.visibility = View.VISIBLE
            bindingClass.Result.text = "Your answer is correct :) Winner! It is: the beach :)"
        }
        bindingClass.ForthOption.setOnClickListener{
            bindingClass.Result.visibility = View.VISIBLE
            bindingClass.Result.text = "Your answer isn't correct :( Try again!"
        }
    }
}