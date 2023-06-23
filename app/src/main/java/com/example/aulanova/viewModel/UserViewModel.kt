package com.example.aulanova.viewModel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import com.example.aulanova.entity.User
import com.example.aulanova.repository.UserRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository, private val context: Context) : ViewModel() {
    var password by mutableStateOf("")
    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var isNameValid by mutableStateOf(true)

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

    private fun validateFields() {
        isNameValid = name.isNotEmpty()
        if (!isNameValid) {
            throw Exception("O campo nome não pode estar vazio!")
        }
    }

    fun register(onSuccess: () -> Unit, onSuccessMessage: String) {
        try {
            validateFields()
            val user = User(name = name, password = password, email = email)
                repository.addUser(user)
                showSuccessMessage(onSuccessMessage)
                onSuccess()
        }
        catch (e: Exception) {
            viewModelScope.launch {
                _toastMessage.emit(e.message?: "Falha no cadastro!")

            }
        }
    }

    private fun showSuccessMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun login(onSuccess: () -> Unit, onFail: () -> Unit) {
        viewModelScope.launch {
            val userFound = repository.findUserByUsernameAndPassword(name, password)
            if (userFound == null) {
                onFail()
            }
            else {
                onSuccess()
            }
        }
    }
}