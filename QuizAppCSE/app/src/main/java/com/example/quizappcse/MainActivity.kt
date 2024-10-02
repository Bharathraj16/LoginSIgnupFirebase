package com.example.quizappcse

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizappcse.Admin.AdminActivity
import com.example.quizappcse.databinding.ActivityMainBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.FirebaseDatabase.getInstance

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var QuizModelList:MutableList<QuizModel>
    lateinit var adapter: QuizListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        //enableEdgeToEdge()
        setContentView(binding.root)

        QuizModelList= mutableListOf()
        getDataFromFirebase()
    }

    private fun setupRecyclerView(){
        binding.progressBar.visibility=View.GONE
        adapter=QuizListAdapter(QuizModelList)
        binding.recyclerView.layoutManager= LinearLayoutManager(this)
        binding.recyclerView.adapter=adapter
    }
    private fun getDataFromFirebase(){
        binding.progressBar.visibility = View.VISIBLE
        getInstance("https://quizapp-1c9c4-default-rtdb.asia-southeast1.firebasedatabase.app/").reference
            .get()
            .addOnSuccessListener { dataSnapshot->
                if(dataSnapshot.exists()){
                    for (snapshot in dataSnapshot.children){
                        val quizModel = snapshot.getValue(QuizModel::class.java)
                        if (quizModel != null) {
                            QuizModelList.add(quizModel)
                        }
                    }
                }
                setupRecyclerView()
            }
            .addOnFailureListener { exception ->
            Log.e("FirebaseError", "Failed to retrieve data: ${exception.message}")
        }

    }
}