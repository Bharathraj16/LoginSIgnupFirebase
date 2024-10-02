package com.example.quizappcse.Admin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizappcse.R

class AdminActivity : AppCompatActivity() {

    private lateinit var firebaseHelper: FirebaseHelper
    private lateinit var topicEditText: EditText
    private lateinit var addTopicButton: Button
    private lateinit var addQuestionButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        firebaseHelper = FirebaseHelper()

        topicEditText = findViewById(R.id.topicEditText)
        addTopicButton = findViewById(R.id.addTopicButton)
        addQuestionButton = findViewById(R.id.addQuestionButton)

        addTopicButton.setOnClickListener {
            val topic = topicEditText.text.toString()
            if (topic.isNotEmpty()) {
                firebaseHelper.addTopic(topic, {
                    Toast.makeText(this, "Topic added", Toast.LENGTH_SHORT).show()
                }, {
                    Toast.makeText(this, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
                })
            }
        }

        addQuestionButton.setOnClickListener {
            val intent = Intent(this, AddQuestionActivity::class.java)
            startActivity(intent)
        }
    }
}
