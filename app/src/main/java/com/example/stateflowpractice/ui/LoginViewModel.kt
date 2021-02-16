package com.example.stateflowpractice.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState.Empty)
    val loginUiState: StateFlow<LoginUiState> = _loginUiState

    fun login(email: String, password: String) = viewModelScope.launch {
        _loginUiState.value = LoginUiState.Loading
        delay(2000L)
        if (email == "leo" || email == "Leo" && password == "1234"){
            _loginUiState.value = LoginUiState.Success
        } else {
            _loginUiState.value = LoginUiState.Error("Wrong email and password")
        }
    }

    sealed class LoginUiState{
        object Success: LoginUiState()
        data class Error(val message: String): LoginUiState()
        object Loading : LoginUiState()
        object Empty : LoginUiState()
    }


}