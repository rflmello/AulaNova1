package com.example.aulanova.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aulanova.entity.Despesa
import com.example.aulanova.repository.DespesaRepository
import kotlinx.coroutines.launch

class DespesaViewModel(
    private val repository: DespesaRepository): ViewModel() {

    var id by mutableStateOf(0)
    var data by mutableStateOf("")
    var valor by mutableStateOf(0.0)
    var descricao by mutableStateOf("")
    var tipo by mutableStateOf("")
    var viagemId by mutableStateOf(0)

    fun register() {
        val despesa = Despesa(data, valor, descricao, tipo, viagemId)
        viewModelScope.launch {
            repository.save(despesa)
        }
    }

    fun DespesasFindAll(viagemId: Int, onSuccess: (despesaQry: List<Despesa>) -> Unit) {
        viewModelScope.launch {
            val despesaQry = repository.findAllByViagem(viagemId)
            onSuccess(despesaQry)
        }
    }

    fun delete(despesa: Despesa) {
        viewModelScope.launch {
            repository.delete(despesa)
        }
    }
}