package com.example.aulanova

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenManager(val route: String,
                           val name: String,
                           val icon: ImageVector) {

    object Home : ScreenManager("home", "Cadastrar", Icons.Filled.Add)
    object Viagem : ScreenManager("viagem", "Viagens", Icons.Filled.List)
    object Sobre : ScreenManager("sobre", "Sobre", Icons.Filled.Info)
    object Login : ScreenManager("login", "Login", Icons.Filled.Info)
    object Register : ScreenManager("register", "Register", Icons.Filled.Info)
    object RegisterViagem : ScreenManager("registerViagem", "RegisterViagem", Icons.Filled.Info)
    object Despesa: ScreenManager("despesa", "Despesa", Icons.Filled.Info)
    object RegisterDespesa: ScreenManager("registerDespesa", "RegisterDespesa", Icons.Filled.Info)

}