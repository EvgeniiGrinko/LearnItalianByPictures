package com.example.learnitalianbypictures

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.learnitalianbypictures.databinding.ActivitySentecesBinding

class SentencesActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivitySentecesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingClass = ActivitySentecesBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        val sentences = intent.getStringArrayExtra("sentences")
        if (sentences != null) {
            bindingClass.sentenceOne.text = sentences[0]
            bindingClass.sentenceTwo.text = sentences[1]

        }
    }
     fun onClickBack(view: View){
         intent.putExtra("sentences", "red")
         setResult(RESULT_OK, intent)
         finish()
     }
}