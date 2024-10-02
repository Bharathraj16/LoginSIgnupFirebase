package com.example.quizappcse.Admin


import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

data class Question(
    val questionId: String? = null,
    val question: String = "",
    val options: List<String> = listOf(),
    val correctAnswer: String = ""
)

class FirebaseHelper {

    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("Topics")

    fun addTopic(topicName: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val topicReference = database.child(topicName)
        topicReference.setValue(topicName).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onSuccess()
            } else {
                task.exception?.let { onFailure(it) }
            }
        }
    }

    fun addQuestion(topicName: String, question: Question, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val questionId = database.child(topicName).push().key!!
        val questionReference = database.child(topicName).child(questionId)
        val newQuestion = question.copy(questionId = questionId)
        questionReference.setValue(newQuestion).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onSuccess()
            } else {
                task.exception?.let { onFailure(it) }
            }
        }
    }

    fun editQuestion(topicName: String, question: Question, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        question.questionId?.let {
            database.child(topicName).child(it).setValue(question).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    task.exception?.let { onFailure(it) }
                }
            }
        }
    }

    fun deleteQuestion(topicName: String, questionId: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        database.child(topicName).child(questionId).removeValue().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onSuccess()
            } else {
                task.exception?.let { onFailure(it) }
            }
        }
    }
}
