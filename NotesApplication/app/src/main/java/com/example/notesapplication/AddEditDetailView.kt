package com.example.notesapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.notesapplication.data.Wish
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: WishViewModel,
    navController: NavController
){
    val snackMessage= remember {
        mutableStateOf("")
    }
    val scope= rememberCoroutineScope()
    val scaffoldState= rememberScaffoldState()
    if(id!=0L){
        val wish=viewModel.getAWishById(id).collectAsState(initial = Wish(0L,"",""))
        viewModel.wishTitleState=wish.value.title
        viewModel.wishDescriptionState=wish.value.description
    }else{
        viewModel.wishTitleState=""
        viewModel.wishDescriptionState=""
    }

    Scaffold(topBar = {AppBarView(title =
    if(id != 0L) stringResource(id = R.string.update_wish)
    else stringResource(id = R.string.add_wish)
    ) {navController.navigateUp()}
    },

        ) {
        Column(modifier = Modifier
            .padding(it)
            .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Spacer(modifier = Modifier.height(10.dp))

            WishTextFieldTitle(label = "Title",
                value = viewModel.wishTitleState,
                onValueChanged = {
                    viewModel.onWishTitleChanged(it)
                } )

            Spacer(modifier = Modifier.height(10.dp))

            WishTextFieldDescription(label = "Description",
                value = viewModel.wishDescriptionState,
                onValueChanged = {
                    viewModel.onWishDescriptionChanged(it)
                } )

            Spacer(modifier = Modifier.height(10.dp))
            Button(
                contentPadding = PaddingValues(16.dp),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(Color.Black),
                onClick= {
                if (viewModel.wishTitleState.isNotEmpty() &&
                    viewModel.wishDescriptionState.isNotEmpty()
                ) {
                    //TODO UpdateWish
                    if (id != 0L) {
                        viewModel.updateWish(
                            Wish(
                                id = id,
                                title = viewModel.wishTitleState.trim(),
                                description = viewModel.wishDescriptionState.trim()
                            )
                        )
                    } else {
                        // TODO AddWish
                        viewModel.addWish(
                            Wish(
                                title = viewModel.wishTitleState.trim(),
                                description = viewModel.wishDescriptionState.trim()
                            )
                        )
                        snackMessage.value = "Wish has been created"
                    }
                }else{
                    snackMessage.value="Enter fields to create a wish"
            }
                scope.launch {
                    navController.navigateUp()
                }


            }){

                Text(
                    text = if (id != 0L) stringResource(id = R.string.update_wish)
                    else stringResource(
                        id = R.string.add_wish
                    ),
                    style = TextStyle(
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )

                )
            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishTextFieldTitle(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit
){
    androidx.compose.material.OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        shape = OutlinedTextFieldDefaults.shape,
        label = { Text(text = label, color = Color.Black) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
            .height(80.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
            // using predefined Color
            //textColor = Color.Black,
            // using our own colors in Res.Values.Color
            focusedBorderColor = colorResource(id = R.color.black),
            unfocusedBorderColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.black),
            unfocusedLabelColor = colorResource(id = R.color.black),
        )


    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishTextFieldDescription(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit
){
    androidx.compose.material.OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        shape = OutlinedTextFieldDefaults.shape,
        label = { Text(text = label, color = Color.Black) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
            .height(180.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.black),
            unfocusedBorderColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.black),
            unfocusedLabelColor = colorResource(id = R.color.black)
        )


    )
}

