package com.example.day3practice.BasicJetpackCompose

import android.widget.Button
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/*fun PracticeButtons(){
    val scaffoldState= rememberScrollState()
    var textFieldState= remember {
        mutableStateOf("")
    }
    val scope= rememberCoroutineScope()
    Scaffold(modifier = Modifier.fillMaxSize(),
        scaffoldState=scaffoldState) { it ->
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center, modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 30.dp
                )) {
            TextField(value = textFieldState,
                label = { Text(text = "Enter your name")},
                onValueChange ={
                    textFieldState=it
                }, singleLine = true, modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                scope.launch {
                    scaffoldState.snackbootomSheet
                        .showSnackbar("Hello $textFieldState")
                }
            }) {
                Text(text = "Click")
            }

        }
    }
}*/