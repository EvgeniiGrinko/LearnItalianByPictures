package com.example.learnitalianbypictures

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.learnitalianbypictures.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMainBinding
    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        // Handle the returned Uri
    }
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
        bindingClass.buttonToReadSenteces.visibility = View.GONE

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
        bindingClass.buttonToReadSenteces.visibility = View.VISIBLE
    }

    fun onClickReadSentences(view: View) {
        val intent = Intent(this, SentencesActivity::class.java)
        intent.putExtra("sentences",arrayOf(
            "Durante la vacanza mi piaceva alzarmi all'alba e farmi lunghe passeggiate in spiaggia.",
            "Vorrei stare seduta con te sulla spiaggia, così avrei il mare di fronte, in cielo sopra e accanto a me il Paradiso... Tu! "
        ))
        resultLauncher.launch(intent)
    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            bindingClass.buttonToReadSenteces.visibility = View.GONE

            bindingClass.NextQuestionId.visibility = View.VISIBLE
        }
    }


}