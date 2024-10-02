package com.example.day3practice.BasicJetpackCompose

import android.accounts.AuthenticatorDescription
import android.icu.text.CaseMap.Title
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier=Modifier
){
    Card(
        modifier=Modifier.width(200.dp).padding(top = 20.dp),
        shape = RoundedCornerShape(16.dp)
    ){
        Box (
            modifier=Modifier.height(200.dp)
        ){
            Image(painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize())
            Box (modifier= Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        startY = 300f
                    )
                ))
            Box(modifier= Modifier
                .fillMaxSize()
                .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ){
                Text(text = title, style = TextStyle(color = Color.White,
                    fontSize = 16.sp))

            }
        }
    }
}
/*
val painter= painterResource(id = R.drawable.virat)
            val description="He is playing in the Ground"
            val title="He is playing in the Ground"
            ImageCard(painter = painter, contentDescription = description, title = title)
 */