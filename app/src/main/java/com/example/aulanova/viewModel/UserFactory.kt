package com.example.aulanova.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aulanova.repository.UserRepository

class UserFactory(val app:Application, private val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = UserRepository(app)
        val model = UserViewModel(repository, context)
        return model as T
    }
}