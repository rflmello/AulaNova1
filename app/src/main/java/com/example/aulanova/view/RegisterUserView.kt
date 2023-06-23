package com.example.aulanova.view

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.aulanova.ScreenManager
import com.example.aulanova.components.PasswordField
import com.example.aulanova.viewModel.UserFactory
import com.example.aulanova.viewModel.UserViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RegisterUserView(navController: NavController) {
    val ctx = LocalContext.current
    val app = ctx.applicationContext as Application
    val context = LocalContext.current.applicationContext as Context
    val user: UserViewModel = viewModel(factory = UserFactory(app, context))
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        user.toastMessage.collectLatest {
            Toast.makeText(ctx, it, Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier.padding(60.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Registrar Usuário",
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Center,
            color = Color.Blue,
        )

        OutlinedTextField(
            value = user.name,
            onValueChange = { user.name = it},
            isError = !user.isNameValid,
            label = {
                Text(text = "Nome")
            },
        )

        PasswordField(
            value = user.password,
            onChange = { user.password = it },
            modifier = Modifier.padding(10.dp)
        )

        OutlinedTextField(modifier = Modifier.padding(10.dp),
            value = user.email,
            label = {
                Text(text = "Email")
            },
            onValueChange = { user.email = it; })

            OutlinedButton(modifier = Modifier.padding(all = 5.dp), onClick = {
                navController.navigate(ScreenManager.Login.route) {

                }
            })
            {
                Text(text = "Voltar")
            }

            OutlinedButton(modifier = Modifier.padding(all = 5.dp), onClick = {
                focusManager.clearFocus()
                user.register(onSuccess = {}, onSuccessMessage = "Usuário Registrado!")
                navController.navigate(ScreenManager.Login.route) {

                }
            })
            {
                Text(text = "Cadastrar")
            }
        }
    }


