package com.example.quizappcse.Admin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizappcse.R

class AddQuestionActivity : AppCompatActivity() {

    private lateinit var questionEditText: EditText
    private lateinit var option1EditText: EditText
    private lateinit var option2EditText: EditText
    private lateinit var option3EditText: EditText
    private lateinit var option4EditText: EditText
    private lateinit var correctAnswerEditText: EditText
    private lateinit var addQuestionButton: Button

    private lateinit var firebaseHelper: FirebaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_question)

        firebaseHelper = FirebaseHelper()

        questionEditText = findViewById(R.id.questionEditText)
        option1EditText = findViewById(R.id.option1EditText)
        option2EditText = findViewById(R.id.option2EditText)
        option3EditText = findViewById(R.id.option3EditText)
        option4EditText = findViewById(R.id.option4EditText)
        correctAnswerEditText = findViewById(R.id.correctAnswerEditText)
        addQuestionButton = findViewById(R.id.addQuestionButton)

        addQuestionButton.setOnClickListener {
            val question = questionEditText.text.toString()
            val options = listOf(
                option1EditText.text.toString(),
                option2EditText.text.toString(),
                option3EditText.text.toString(),
                option4EditText.text.toString()
            )
            val correctAnswer = correctAnswerEditText.text.toString()

            if (question.isNotEmpty() && options.all { it.isNotEmpty() } && correctAnswer.isNotEmpty()) {
                val newQuestion = Question(question = question, options = options, correctAnswer = correctAnswer)
                firebaseHelper.addQuestion("SelectedTopic", newQuestion, {
                    Toast.makeText(this, "Question added", Toast.LENGTH_SHORT).show()
                }, {
                    Toast.makeText(this, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
                })
            }
        }
    }
}
