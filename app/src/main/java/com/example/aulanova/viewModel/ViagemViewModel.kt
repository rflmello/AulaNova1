package com.example.aulanova.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aulanova.entity.TipoViagem
import com.example.aulanova.entity.Viagem
import com.example.aulanova.repository.ViagemRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ViagemViewModel(
    private val repository: ViagemRepository): ViewModel()  {

    var id by mutableStateOf(0)
    var destino by mutableStateOf("")
    private var _tipo by mutableStateOf(TipoViagem.NEGOCIO)
    var tipo: TipoViagem
        get() = _tipo
        set(value) {
            if (value == TipoViagem.LAZER || value == TipoViagem.NEGOCIO) {
                _tipo = value
            } else {
                throw IllegalArgumentException("Tipo de viagem inválido")
            }
        }
    var data_inicio by mutableStateOf("")
    var data_final by mutableStateOf("")
    var orcamento by mutableStateOf(0.0)
    var userID by mutableStateOf(0)

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

    fun register() {
        val viagem = Viagem(destino, tipo, data_inicio, data_final, 1, 0.0)
        viewModelScope.launch {
            repository.save(viagem)
        }
    }

    fun ViagemFindAll(onSuccess: (viagemQry: List<Viagem>) -> Unit) {
        viewModelScope.launch {
            val viagemQry = repository.findAll()
            onSuccess(viagemQry)
        }
    }

    fun delete(viagem: Viagem) {
        viewModelScope.launch {
            repository.delete(viagem)
        }
    }
}