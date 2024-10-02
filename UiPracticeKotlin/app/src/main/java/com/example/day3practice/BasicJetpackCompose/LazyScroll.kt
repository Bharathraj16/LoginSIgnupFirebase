package com.example.day3practice.BasicJetpackCompose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LazyScroll(){
    LazyColumn(modifier = Modifier.fillMaxSize()){
        items(200){
            Text(text = "Item $it")
        }
    }
}