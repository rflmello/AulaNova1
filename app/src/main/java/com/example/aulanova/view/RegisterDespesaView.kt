package com.example.aulanova.view

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.aulanova.ScreenManager
import com.example.aulanova.viewModel.DespesaFactory
import com.example.aulanova.viewModel.DespesaViewModel

@Composable
fun RegisterDespesaView(navController: NavController, id: Int?) {
    val ctx = LocalContext.current
    val app = ctx.applicationContext as Application
    val despesa: DespesaViewModel = viewModel(factory = DespesaFactory(app))

    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Column(Modifier.padding(all= 60.dp)) {
            Text(
                text = "Cadastro de Despesas",
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center,
                color = Color.Blue,
            )

            OutlinedTextField(modifier = Modifier.padding(10.dp),
                value = despesa.data,
                label = {
                    Text(text = "Data")
                },
                onValueChange = { despesa.data =it; })

            OutlinedTextField(modifier = Modifier.padding(10.dp),
                value = despesa.valor.toString(),
                label = {
                    Text(text = "Valor da despesa")
                },
                onValueChange = { despesa.valor = it.toDoubleOrNull() ?: 0.0 })

            OutlinedTextField(modifier = Modifier.padding(10.dp),
                value = despesa.descricao,
                label = {
                    Text(text = "Descrição")
                },
                onValueChange = { despesa.descricao = it; })

            OutlinedTextField(modifier = Modifier.padding(10.dp),
                value = despesa.tipo,
                label = {
                    Text(text = "Tipo da despesa")
                },
                onValueChange = { despesa.tipo = it; })
        }


        OutlinedButton(modifier = Modifier.padding(), onClick =  {
            navController.navigate(ScreenManager.Despesa.route + "/" + id) {
                if (id != null) {
                    despesa.viagemId = id
                }
                despesa.register()
            }
        })
        {
            Text(text = "Salvar")
        }
        OutlinedButton(modifier = Modifier.padding(),onClick =  {
            navController.navigate(ScreenManager.Despesa.route + "/" + id) { }
        })
        {
            Text(text = "Voltar")
        }

        }
    }