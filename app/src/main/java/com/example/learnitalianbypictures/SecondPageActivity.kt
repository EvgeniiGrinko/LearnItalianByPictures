package com.example.learnitalianbypictures

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.learnitalianbypictures.databinding.ActivitySecondPageBinding

class SecondPageActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivitySecondPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivitySecondPageBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        bindingClass.Option1.setText(R.string.la_persona)
        bindingClass.Option2.setText(R.string.la_citta)
        bindingClass.Option3.setText(R.string.bicicletta)
        bindingClass.Option4.setText(R.string.uomo)
        bindingClass.NextQuestion2Id.visibility = View.GONE
        bindingClass.NextQuestion2Id.setText(R.string.next)


        bindingClass.Option1.setOnClickListener{
            this.showIncorrectMessageAnswer()
        }
        bindingClass.Option2.setOnClickListener{
            this.showCorrectMessageAnswer()
        }
        bindingClass.Option3.setOnClickListener{
            this.showIncorrectMessageAnswer()
        }
        bindingClass.Option4.setOnClickListener{
            this.showIncorrectMessageAnswer()
        }
    }
    fun onClickGoToThirdActivity(view: View){

    }
    private fun showIncorrectMessageAnswer(){
        bindingClass.AnswerCommentId.setText(R.string.incorrect_answer)
        bindingClass.AnswerCommentId.visibility = View.VISIBLE

    }
    private fun showCorrectMessageAnswer(){
        bindingClass.Option1.visibility = View.GONE
        bindingClass.Option2.visibility = View.GONE
        bindingClass.Option3.visibility = View.GONE
        bindingClass.Option4.visibility = View.GONE
        bindingClass.AnswerCommentId.visibility = View.VISIBLE
        bindingClass.AnswerCommentId.setText(R.string.correct_answer)
        bindingClass.NextQuestion2Id.visibility = View.VISIBLE
    }

}