package com.example.aulanova.view

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.AccountBox
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
import com.example.aulanova.components.BottomMenu
import com.example.aulanova.entity.Despesa
import com.example.aulanova.viewModel.DespesaFactory
import com.example.aulanova.viewModel.DespesaViewModel
import java.text.DecimalFormat

@Composable
fun Despesa(navController: NavController, id: Int?) {
    val ctx = LocalContext.current
    val app = ctx.applicationContext as Application
    val despesaViewModel: DespesaViewModel = viewModel(factory = DespesaFactory(app))
    var despesas: List<Despesa> = ArrayList();

    if (id != null) {
        despesaViewModel.DespesasFindAll(id, onSuccess = {
            despesas = it
        })
    }

    Scaffold(
        topBar = {
            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally) {

                Text(
                    text = "Despesas Cadastradas",
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Center,
                    color = Color.Blue,
                )
            LazyColumn(){
                items(items = despesas) {
                        p -> SpentCard(p, navController)
                }
            }
        }},

        bottomBar = {
            BottomMenu(navController = navController)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(ScreenManager.RegisterDespesa.route + "/" + id) {

                }
            },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(Icons.Filled.Add,"")
            }
        }
    ) { }
}

@Composable
fun SpentCard(despesa: Despesa, navController: NavController) {
    val df = DecimalFormat("0.00")
    val ctx = LocalContext.current
    val app = ctx.applicationContext as Application
    val despesaViewModel: DespesaViewModel = viewModel(factory = DespesaFactory(app))

    Card(
        elevation = 8.dp,
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 8.dp),
    ) {
        Row() {
            Column(modifier = Modifier
                .padding(16.dp)
                .weight(1f)
            ) {
                Text(text = "Data: ${despesa.data}")
                Text(text = "Descrição: ${despesa.descricao}")
            }
            Column(modifier = Modifier
                .padding(16.dp)
                .weight(1f)
            ) {
                Text(text = "Valor: R$ ${df.format(despesa.valor)}")
                Text(text = "Local: ${despesa.tipo}")
            }
            Button(onClick = {
                despesaViewModel.delete(despesa)
                navController.navigate(ScreenManager.Despesa.route + "/" + despesa.id) {

                }
            }) {
                Icon(Icons.Default.Delete, contentDescription = "Localized description")
            }
        }
    }
}