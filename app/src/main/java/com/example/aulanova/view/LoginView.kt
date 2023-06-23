package com.example.aulanova.view

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.aulanova.ScreenManager
import com.example.aulanova.R
import com.example.aulanova.components.PasswordField
import com.example.aulanova.viewModel.UserFactory
import com.example.aulanova.viewModel.UserViewModel

@Composable
fun LoginView(navController: NavController) {
    val ctx = LocalContext.current
    val app = ctx.applicationContext as Application
    val context = LocalContext.current
    val user: UserViewModel = viewModel(factory = UserFactory(app, context))


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "APP Viagens",
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Center,
            color = Color.Blue,
        )

        Image(
            painter = painterResource(id = R.drawable.viagem),
            contentDescription = "Login",
        )

        OutlinedTextField(
            modifier = Modifier.padding(20.dp),
            value = user.name,

            onValueChange = { user.name = it; },
            label = {
                Text(text = "Nome")
            }
        )

        PasswordField(value = user.password, modifier = Modifier.padding(10.dp), onChange = { user.password = it});

        OutlinedButton(modifier = Modifier.padding(all= 10.dp), onClick =  {
            user.login(onSuccess = {
                Toast.makeText(context, "Login Realizado", Toast.LENGTH_SHORT).show();
                navController.navigate(ScreenManager.Viagem.route) { }
            }, onFail = {
                Toast.makeText(context, "Login Inválido", Toast.LENGTH_LONG).show();
            })
        })
        {
            Text(text = "Login")
        }

        Row(Modifier.padding(all= 50.dp)) {
            OutlinedButton(modifier = Modifier.padding(all= 15.dp),onClick =  {
                navController.navigate(ScreenManager.Register.route) {

                }
            })
            {
                Text(text = "Registrar")
            }
        }
    }
}