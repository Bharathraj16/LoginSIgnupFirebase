package com.example.day3practice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Screen1(navController: NavController){
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Screen 1")
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { navController.navigate("Screen_2")}) {
            Text(text = "GO to Screen 2")
        }
    }
}
/*val navController = rememberNavController()
NavHost(navController = navController, startDestination = "Screen_1") {
    composable("Screen_1") { Screen1(navController = navController) }
    composable("Screen_2") { Screen2()}

}*/