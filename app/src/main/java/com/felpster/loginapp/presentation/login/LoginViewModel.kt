package com.felpster.loginapp.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felpster.loginapp.domain.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class LoginViewState {

    data object Success : LoginViewState()
    data object Loading : LoginViewState()
    data class Error(val message: String) : LoginViewState()
}

sealed class LoginViewEvent {
    data class onLoginClick(val username: String, val password: String) : LoginViewEvent()
}

class LoginViewModel(
    private val loginRepository: LoginRepository,
) : ViewModel() {

    val state = MutableStateFlow<LoginViewState?>(null)

    fun onEvent(event: LoginViewEvent) {
        when (event) {
            is LoginViewEvent.onLoginClick -> doLogin(event)
        }
    }

    private fun doLogin(event: LoginViewEvent.onLoginClick) {
        viewModelScope.launch {
            loginRepository
                .authenticate(
                    username = event.username,
                    password = event.password,
                )
                .onSuccess {
                    state.value = LoginViewState.Success
                }
                .onFailure {
                    state.value = LoginViewState.Error(it.message ?: "Ops, something went wrong!!!")
                }
        }
    }

}