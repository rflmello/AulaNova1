package com.example.aulanova.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aulanova.repository.DespesaRepository


class DespesaFactory(val app: Application): ViewModelProvider.Factory  {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = DespesaRepository(app)
        val model = DespesaViewModel(repository)
        return model as T
    }
}