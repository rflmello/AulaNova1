package com.example.aulanova.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aulanova.R
import com.example.aulanova.ScreenManager
import com.example.aulanova.components.BottomMenu

@Composable
fun Sobre(navController: NavController) {
    Scaffold(
        topBar = {
            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally) {

                Text(
                    text = "Sobre o APP",
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Center,
                    color = Color.Blue,
                )

                Text(
                    text = stringResource(id = R.string.sobre),
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray,
                )

                OutlinedButton(modifier = Modifier.padding(all= 5.dp), onClick =  {
                    navController.navigate(ScreenManager.Login.route) {

                    }
                })
                {
                    Text(text = "Logout")
                }
            }
        },
        bottomBar = {
            BottomMenu(navController = navController)
        }
    )
    { }
}