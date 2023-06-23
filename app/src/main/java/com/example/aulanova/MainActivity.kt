package com.example.aulanova

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aulanova.ui.theme.AulaNovaTheme
import com.example.aulanova.view.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AulaNovaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp();
                }
            }
        }
    }
}

@Composable
fun LoginNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = ScreenManager.Login.route) {
        composable(ScreenManager.Login.route) { LoginView(navController) }
        composable(ScreenManager.Sobre.route) { Sobre(navController) }
        composable(ScreenManager.Register.route) { RegisterUserView(navController) }
        composable(ScreenManager.Home.route) { Home(navController) }
        composable(ScreenManager.Viagem.route) { Viagem(navController) }
        composable(ScreenManager.RegisterViagem.route) { RegisterViagemView(navController) }
        composable(ScreenManager.Despesa.route + "/" + "{viagemId}",
            arguments = listOf(
                navArgument("viagemId") {
                    type = NavType.IntType
                })
        )
        {
            val id = it.arguments?.getInt ("viagemId")
            Despesa(navController, id)
        }

        composable(
            ScreenManager.RegisterDespesa.route + "/" + "{viagemId}",
            arguments = listOf(
                navArgument("viagemId") {
                    type = NavType.IntType
                })
        )
        {
            val id = it.arguments?.getInt ("viagemId")
            RegisterDespesaView(navController, id)
        }
    }
}

@Composable
fun MyApp(){
    val navController = rememberNavController()
    LoginNavigation(navController)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    AulaNovaTheme {
        MyApp();
    }
}