package com.example.day3practice.BasicJetpackCompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun StyleText(){
    Box (modifier = Modifier
        .fillMaxSize()
        .background(Color.Gray)){
        Text(text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color.Green,
                    fontSize = 50.sp
                )
            ){
                append("J")
            }
            append("etpack")
            withStyle(style = SpanStyle(
                color = Color.Green, fontSize = 50.sp
            )
            ){
                append("C")
            }
            append("ompose")
        })
    }
}