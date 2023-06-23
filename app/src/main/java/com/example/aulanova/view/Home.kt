package com.example.aulanova.view

import android.app.Application
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.aulanova.ScreenManager
import com.example.aulanova.entity.TipoViagem
import com.example.aulanova.viewModel.ViagemFactory
import com.example.aulanova.viewModel.ViagemViewModel
import androidx.compose.material.OutlinedButton as OutlinedButton

@Composable
fun Home(navController: NavHostController ) {
    val ctx = LocalContext.current
    val app = ctx.applicationContext as Application
    var selectedOption = remember { mutableStateOf("") }

    val viagem: ViagemViewModel = viewModel(factory = ViagemFactory(app))

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(Modifier.padding()) {
            Text(
                text = "Cadastrar Viagens",
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center,
                color = Color.Blue,
            )

            OutlinedTextField(modifier = Modifier.padding(10.dp),
                value = viagem.destino,
                label = {
                    Text(text = "Destino")
                },
                onValueChange = { viagem.destino = it; })

/*            OutlinedTextField(modifier = Modifier.padding(10.dp),
                value = viagem.orcamento.toString(),
                label = {
                    Text(text = "Valor da viagem")
                },
                onValueChange = { viagem.orcamento = 100.0 })*/

            OutlinedTextField(modifier = Modifier.padding(10.dp),
                value = viagem.data_inicio,
                label = {
                    Text(text = "Data de inicio")
                },
                onValueChange = { viagem.data_inicio = it; })

            OutlinedTextField(modifier = Modifier.padding(10.dp),
                value = viagem.data_final,
                label = {
                    Text(text = "Data fim")
                },
                onValueChange = { viagem.data_final = it; })

            Row(Modifier.padding(start = 10.dp)) {
                Text(text = "Lazer")
                RadioButton(modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                    selected = selectedOption.value == "LAZER",
                    onClick = {
                        selectedOption.value = "LAZER"
                    })
                Text(text = "Negócios")
                RadioButton(modifier = Modifier.padding(start = 10.dp),
                    selected = selectedOption.value == "NEGOCIO",
                    onClick = {
                        selectedOption.value = "NEGOCIO"
                    })
            }

            Row(Modifier.padding(all = 50.dp)) {
                OutlinedButton(modifier = Modifier.padding(all = 1.dp), onClick = {
                    navController.navigate(ScreenManager.Viagem.route) { }
                })
                {
                    Text(text = "Voltar")
                }

                OutlinedButton(modifier = Modifier.padding(all = 1.dp), onClick = {
                    navController.navigate(ScreenManager.Viagem.route) {
                        if (selectedOption.value == "LAZER") {
                            viagem.tipo = TipoViagem.LAZER
                        } else {
                            viagem.tipo = TipoViagem.NEGOCIO
                        }
                        viagem.register()
                    }
                })
                {
                    Text(text = "Salvar")
                }
            }
        }
    }
}