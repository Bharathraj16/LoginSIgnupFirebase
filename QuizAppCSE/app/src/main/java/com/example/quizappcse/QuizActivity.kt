package com.example.quizappcse

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizappcse.databinding.ActivityQuizBinding
import com.example.quizappcse.databinding.ScoreDialogBinding

class QuizActivity : AppCompatActivity(), View.OnClickListener {
    companion object{
        var questionModelList:List<QuestionModel> = listOf()
        var time:String=""
    }
    var currentQuestionIndex=0
    var selectedAnswer=""
    var score=0

    lateinit var binding: ActivityQuizBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btn0.setOnClickListener(this@QuizActivity)
            btn1.setOnClickListener(this@QuizActivity)
            btn2.setOnClickListener(this@QuizActivity)
            btn3.setOnClickListener(this@QuizActivity)
            nextBtn.setOnClickListener(this@QuizActivity)
        }
        loadQuestion()
        startTimer()

    }

    private fun startTimer(){
        val totalTimeMillis=time.toInt()*60000L
        object : CountDownTimer(totalTimeMillis,1000L){
            @SuppressLint("DefaultLocale")
            override fun onTick(milliUtilsFinished: Long) {
                val seconds = milliUtilsFinished / 1000
                val minutes = seconds/60
                val remainingSeconds = seconds % 60
                binding.timerIndicatorTextview.text=String.format("%02d:%02d",minutes,remainingSeconds)

            }

            override fun onFinish() {
                //finish the quiz
            }
        }.start()
    }

    @SuppressLint("SetTextI18n")
    private fun loadQuestion(){
        selectedAnswer=""
        if (currentQuestionIndex== questionModelList.size){
            finishQuiz()
            return
        }
        binding.apply {
            questionIndicatorTextview.text="Question ${currentQuestionIndex+1}/ ${questionModelList.size}"
            questionTextview.text=questionModelList[currentQuestionIndex].question
            questionProgressIndicator.progress=(currentQuestionIndex.toFloat()/ questionModelList.size.toFloat()*100).toInt()
            btn0.text=questionModelList[currentQuestionIndex].options[0]
            btn1.text=questionModelList[currentQuestionIndex].options[1]
            btn2.text=questionModelList[currentQuestionIndex].options[2]
            btn3.text=questionModelList[currentQuestionIndex].options[3]
        }
    }

    override fun onClick(view: View?) {
        binding.apply {
            btn0.setBackgroundColor(getColor(R.color.gray))
            btn1.setBackgroundColor(getColor(R.color.gray))
            btn2.setBackgroundColor(getColor(R.color.gray))
            btn3.setBackgroundColor(getColor(R.color.gray))
        }
        val clickedBtn=view as Button
        if (clickedBtn.id==R.id.next_btn){
            if (selectedAnswer.isEmpty()){
                Toast.makeText(applicationContext,"Please select the answer to continue",Toast.LENGTH_SHORT).show()
                return
            }
            if (selectedAnswer== questionModelList[currentQuestionIndex].correctAnswer){
                score++
                Log.i("Score Quiz",score.toString())
            }
            currentQuestionIndex++
            loadQuestion()
        }else{
            selectedAnswer=clickedBtn.text.toString()
            clickedBtn.setBackgroundColor(getColor(R.color.orange))
        }
    }

    private fun finishQuiz(){
        val totalQuestions = questionModelList.size
        val percentage = ((score.toFloat() / totalQuestions.toFloat() ) *100 ).toInt()

        val dialogBinding  = ScoreDialogBinding.inflate(layoutInflater)
        dialogBinding.apply {
            scoreProgressIndicator.progress = percentage
            scoreProgressText.text = "$percentage %"
            if(percentage>60){
                scoreTitle.text = "Congrats! You have passed"
                scoreTitle.setTextColor(Color.BLUE)
            }else{
                scoreTitle.text = "Oops! You have failed"
                scoreTitle.setTextColor(Color.RED)
            }
            scoreSubtitle.text = "$score out of $totalQuestions are correct"
            finishBtn.setOnClickListener {
                finish()
            }
        }

        AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .setCancelable(false)
            .show()

    }
}