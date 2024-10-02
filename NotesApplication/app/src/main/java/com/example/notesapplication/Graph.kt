package com.example.notesapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.notesapplication.data.WishDao
import com.example.notesapplication.data.WishDatabase
import com.example.notesapplication.data.WishRepository

object Graph {
    lateinit var database: WishDatabase
    val WishRepository by lazy {
        WishRepository(wishDao = database.WishDao())
    }
    fun provide(context:Context){
        database=Room.databaseBuilder(context,WishDatabase::class.java,"WishList.db").build()
    }
}