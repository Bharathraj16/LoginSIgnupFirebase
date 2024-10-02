package com.example.day3practice.BasicJetpackCompose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp

@Composable
fun ColumnPractice(){
    Column (modifier = Modifier
        .fillMaxSize()
        .background(Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ){
        Text("Hello")
        Text(text = "World")
    }

}

@Composable
fun RowPractice(){
    Row (modifier = Modifier
        .fillMaxSize()
        .background(Color.Green),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly){
        Text("Hello")
        Text(text = "World")
    }
}

@Composable
fun ModifierPractice(){
    Row (
        //modifier = Modifier.fillMaxHeight(0.5f).fillMaxWidth(0.5f).background(Color.Green),
        //modifier = Modifier.requiredWidth(600.dp).height(500.dp).padding(20.dp).background(Color.Green),
        modifier = Modifier
            .height(500.dp)
            .width(200.dp)
            .background(Color.Green)
            .border(
                5.dp,
                Color.Black
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly){
        Text("Hello",modifier= Modifier
            .offset(50.dp, 30.dp)
            .clickable { } )
        Text(text = "World")
    }
}

@Composable
fun ModifierColPract(){
    Column {
        Text(text = "Hello", modifier = Modifier
            .border(5.dp, Color.Yellow)
            .padding(5.dp)
            .offset(20.dp, 20.dp)
            .border(10.dp, Color.Black)
            .padding(10.dp))
        Text(text = "World")
    }
}