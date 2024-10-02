package com.example.day3practice.LoginUiPractice

import android.graphics.drawable.Icon
import android.graphics.drawable.PaintDrawable
import android.text.InputType
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.day3practice.R

@Composable
fun LoginScreen(
    name:String="Log in",
    descript:String="Welcome back! Pleaase enter your details.",
    painter: Painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24)
){
    var emalName by remember {
        mutableStateOf("")
    }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, top = 24.dp)
    ){
        Image(painter =painter,
            contentDescription = "Previous",
            modifier = Modifier.size(36.dp))
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = name, style = TextStyle(fontSize = 36.sp, fontWeight = FontWeight.Bold))
        Text(text = descript, color = Color(0xff888888),
            style = TextStyle(fontSize = 18.sp,fontWeight = FontWeight.Medium))
        Text(text = "Email",style = TextStyle(fontSize = 18.sp,fontWeight = FontWeight.Medium))
        TextField(value = emalName, onValueChange = {emalName=it},
            label = { Text("Enter your email") },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(end = 16.dp)
                .fillMaxWidth(),
            leadingIcon = { Icons.Filled.Email}
            )
        Text(text = "Password",style = TextStyle(fontSize = 18.sp,fontWeight = FontWeight.Medium))
        TextField(value = emalName, onValueChange = {emalName=it},
            label = { Text("********") },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(end = 16.dp)
                .fillMaxWidth()
                .background(Color.White),
            leadingIcon = { Icons.Filled.Lock},
        )
        options()
        Button(onClick = { /*TODO*/ }, shape = RoundedCornerShape(8.dp),
                modifier = Modifier.width(200.dp)
            ) {
                Text(text = "Login")

        }


    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview(){
    LoginScreen()
}

@Composable
fun options(){
    Box (
    ){
        Row (
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ){
            androidx.compose.material3.Icon(painter = painterResource(id = R.drawable.baseline_check_box_24), contentDescription = "checkBox")
            Text(text = "Remember for 30 days")
            Spacer(modifier = Modifier.width(90.dp))
            Text(text = "Forget password")
        }

    }
}
