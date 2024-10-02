package com.example.quizappcse.Admin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.example.quizappcse.R


class EditQuestionActivity : AppCompatActivity() {

    // Declare Firebase database reference
    private lateinit var databaseReference: DatabaseReference

    // Declare UI components
    private lateinit var editQuestionEditText: EditText
    private lateinit var editOption1EditText: EditText
    private lateinit var editOption2EditText: EditText
    private lateinit var editOption3EditText: EditText
    private lateinit var editOption4EditText: EditText
    private lateinit var editCorrectAnswerEditText: EditText
    private lateinit var saveChangesButton: Button
    private lateinit var deleteQuestionButton: Button

    // Variables to store passed data
    private var topicId: String? = null
    private var questionId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_question)

        // Initialize UI components
        editQuestionEditText = findViewById(R.id.editQuestionEditText)
        editOption1EditText = findViewById(R.id.editOption1EditText)
        editOption2EditText = findViewById(R.id.editOption2EditText)
        editOption3EditText = findViewById(R.id.editOption3EditText)
        editOption4EditText = findViewById(R.id.editOption4EditText)
        editCorrectAnswerEditText = findViewById(R.id.editCorrectAnswerEditText)
        saveChangesButton = findViewById(R.id.saveChangesButton)
        deleteQuestionButton = findViewById(R.id.deleteQuestionButton)

        // Get data passed from the previous activity
        topicId = intent.getStringExtra("topicId")
        questionId = intent.getStringExtra("questionId")

        // Initialize Firebase reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Topics")

        // Load question data from Firebase
        loadQuestionData()

        // Save changes when Save button is clicked
        saveChangesButton.setOnClickListener {
            saveUpdatedQuestion()
        }

        // Delete question when Delete button is clicked
        deleteQuestionButton.setOnClickListener {
            deleteQuestion()
        }
    }

    // Function to load the current question data from Firebase
    private fun loadQuestionData() {
        if (topicId != null && questionId != null) {
            val questionRef = databaseReference.child(topicId!!).child("questions").child(questionId!!)

            // Retrieve current question data
            questionRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val question = snapshot.child("question").value.toString()
                        val option1 = snapshot.child("option1").value.toString()
                        val option2 = snapshot.child("option2").value.toString()
                        val option3 = snapshot.child("option3").value.toString()
                        val option4 = snapshot.child("option4").value.toString()
                        val correctAnswer = snapshot.child("correctAnswer").value.toString()

                        // Pre-fill the fields with current data
                        editQuestionEditText.setText(question)
                        editOption1EditText.setText(option1)
                        editOption2EditText.setText(option2)
                        editOption3EditText.setText(option3)
                        editOption4EditText.setText(option4)
                        editCorrectAnswerEditText.setText(correctAnswer)
                    } else {
                        Toast.makeText(this@EditQuestionActivity, "Question not found", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@EditQuestionActivity, "Failed to load question data", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    // Function to save the updated question data
    private fun saveUpdatedQuestion() {
        val updatedQuestion = editQuestionEditText.text.toString().trim()
        val updatedOption1 = editOption1EditText.text.toString().trim()
        val updatedOption2 = editOption2EditText.text.toString().trim()
        val updatedOption3 = editOption3EditText.text.toString().trim()
        val updatedOption4 = editOption4EditText.text.toString().trim()
        val updatedCorrectAnswer = editCorrectAnswerEditText.text.toString().trim()

        // Validate input
        if (updatedQuestion.isEmpty() || updatedOption1.isEmpty() || updatedOption2.isEmpty() ||
            updatedOption3.isEmpty() || updatedOption4.isEmpty() || updatedCorrectAnswer.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (topicId != null && questionId != null) {
            val questionRef = databaseReference.child(topicId!!).child("questions").child(questionId!!)

            // Update question data in Firebase
            val updatedData = mapOf(
                "question" to updatedQuestion,
                "option1" to updatedOption1,
                "option2" to updatedOption2,
                "option3" to updatedOption3,
                "option4" to updatedOption4,
                "correctAnswer" to updatedCorrectAnswer
            )

            questionRef.updateChildren(updatedData).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Question updated successfully", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Failed to update question", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Function to delete the current question
    private fun deleteQuestion() {
        if (topicId != null && questionId != null) {
            val questionRef = databaseReference.child(topicId!!).child("questions").child(questionId!!)

            // Remove question from Firebase
            questionRef.removeValue().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Question deleted successfully", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Failed to delete question", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
