package com.example.mediationui.ScreenPages

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediationui.R
import com.example.mediationui.ui.theme.AquaBlue
import com.example.mediationui.ui.theme.ButtonBlue
import com.example.mediationui.ui.theme.DarkerButtonBlue
import com.example.mediationui.ui.theme.DeepBlue
import com.example.mediationui.ui.theme.TextWhite
import com.example.mediationui.ui.theme.gothicA1

@Composable
fun HomeScreen(){
    Box(
        modifier= Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ){
        Column {
            GreetingSection()
            //ChipSection(chips = listOf("Sweet sleep","Insomnia","Depression"))
        }
    }
}



@Composable
fun GreetingSection(
    name: String = "Alice"
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            /*Text(
                text = "Good morning,$name",
                style = TextStyle(
                    color = TextWhite,
                    fontFamily = gothicA1,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            )
            Text(
                text = "We wish you have a good day!",
                style = TextStyle(
                    color = AquaBlue,
                    fontFamily = gothicA1,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
            )
        }*/
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
@Composable
fun ChipSection(
    chips:List<String>
) {
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }
    LazyRow {
        items(chips.size) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .clickable {
                        selectedChipIndex = it
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChipIndex == it) ButtonBlue
                        else DarkerButtonBlue
                    )
                    .padding(15.dp)
            ) {
                // Text(text = chips[it], color = TextWhite)
            }
        }
    }
}