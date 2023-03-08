package com.example.learnitalianbypictures

import android.content.Intent
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
        bindingClass.FirstOption.setText(R.string.il_burro)
        bindingClass.SecondOption.setText(R.string.la_macchina)
        bindingClass.ThirdOption.setText(R.string.la_spiaggia)
        bindingClass.ForthOption.setText(R.string.la_testa)
        bindingClass.GuessWord.setText(R.string.guess_answer)
        bindingClass.NextQuestionId.setText(R.string.next)

        bindingClass.FirstOption.setOnClickListener{
            this.showIncorrectMessageAnswer()
        }
        bindingClass.SecondOption.setOnClickListener{
            this.showIncorrectMessageAnswer()
        }
        bindingClass.ThirdOption.setOnClickListener{
            this.showCorrectMessageAnswer()
        }
        bindingClass.ForthOption.setOnClickListener{
            this.showIncorrectMessageAnswer()
        }
    }
    fun onClickGoToSecondActivity(view: View){
        val intent = Intent(this, SecondPageActivity::class.java)
        startActivity(intent)
    }
    private fun showIncorrectMessageAnswer(){
        bindingClass.Result.setText(R.string.incorrect_answer)
        bindingClass.Result.visibility = View.VISIBLE

    }
    private fun showCorrectMessageAnswer(){
        bindingClass.Result.visibility = View.VISIBLE
        bindingClass.FirstOption.visibility = View.GONE
        bindingClass.SecondOption.visibility = View.GONE
        bindingClass.ThirdOption.visibility = View.GONE
        bindingClass.ForthOption.visibility = View.GONE
        bindingClass.Result.setText(R.string.correct_answer)
        bindingClass.NextQuestionId.visibility = View.VISIBLE
    }

}