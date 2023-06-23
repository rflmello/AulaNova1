package com.example.aulanova.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aulanova.repository.ViagemRepository

class ViagemFactory(val app: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = ViagemRepository(app)
        val model = ViagemViewModel(repository)
        return model as T
    }
}