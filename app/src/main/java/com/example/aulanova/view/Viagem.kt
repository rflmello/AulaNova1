package com.example.aulanova.view

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.aulanova.R
import com.example.aulanova.ScreenManager
import com.example.aulanova.components.BottomMenu
import com.example.aulanova.entity.TipoViagem
import com.example.aulanova.entity.Viagem
import com.example.aulanova.viewModel.ViagemFactory
import com.example.aulanova.viewModel.ViagemViewModel
import java.text.DecimalFormat

@Composable
fun Viagem(navController: NavController) {
    val ctx = LocalContext.current
    val app = ctx.applicationContext as Application
    val viagemVM: ViagemViewModel = viewModel(factory = ViagemFactory(app))
    var viagens: List<Viagem> = ArrayList();

    viagemVM.ViagemFindAll(onSuccess = {
        viagens = it
    })

    Scaffold(

        topBar = {
            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = "Viagens Cadastradas",
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center,
                color = Color.Blue,
            )
            LazyColumn(){
                items(items = viagens) {
                        p -> ViagemCard(p, navController)
                }
            }
        }},
        bottomBar = {
            BottomMenu(navController = navController)
        },
    ) { }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ViagemCard(viagem: Viagem, navController: NavController) {
    val df = DecimalFormat("0.00")
    val ctx = LocalContext.current
    val app = ctx.applicationContext as Application
    val viagemVM: ViagemViewModel = viewModel(factory = ViagemFactory(app))


    Card(
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 8.dp),
        onClick = {
            navController.navigate(ScreenManager.Despesa.route + "/" + viagem.id) {

            }
        }
    ) {

        Row() {
            Column(modifier = Modifier
                .padding(2.dp)
                .weight(1f)
            ) {
                val tipoViagem = viagem.tipo

                val imagemResId = if (tipoViagem == TipoViagem.LAZER) {
                    R.drawable.lazer
                } else if (tipoViagem == TipoViagem.NEGOCIO) {
                    R.drawable.negocio
                } else {
                    R.drawable.viagem // Imagem padrão caso o tipo não corresponda a nenhum caso
                }
                Image(
                    painter = painterResource(id = imagemResId),
                    contentDescription = "Viagem"
                )
            }

            Column(modifier = Modifier
                .padding(2.dp)
                .weight(1f)
            ) {
                Text(text = "Destino: ${viagem.destino}")
                Text(text = "Tipo: ${viagem.tipo}")
                Text(text = "R$: ${df.format(viagem.orcamento)}")
            }
            Column(modifier = Modifier
                .padding(2.dp)
                .weight(1f)
            ) {
                Text(text = "Início: ${viagem.data_inicio}")
                Text(text = "Fim: ${viagem.data_final}")

            }
            /*Column(modifier = Modifier
                .padding(2.dp)
                .weight(1f)
            ) {
            Text(
                text = "R$ ${df.format(viagem.orcamento)}"
            )}*/
            Button(onClick = {
                viagemVM.delete(viagem)
                navController.navigate(ScreenManager.Viagem.route) {

                }
            }) {
                Icon(Icons.Default.Delete, contentDescription = "")
            }
        }
    }
}
