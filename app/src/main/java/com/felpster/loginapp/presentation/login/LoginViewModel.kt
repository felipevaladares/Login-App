package com.felpster.loginapp.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felpster.loginapp.domain.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

sealed class LoginViewState {

    data object Success : LoginViewState()
    data class Loading(val message: String?) : LoginViewState()
    data class Error(val message: String? = null) : LoginViewState()
}

sealed class LoginViewEvent {
    data class OnLoginClick(val username: String, val password: String) : LoginViewEvent()
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
) : ViewModel() {

    val state = MutableStateFlow<LoginViewState?>(null)

    fun onEvent(event: LoginViewEvent) {
        when (event) {
            is LoginViewEvent.OnLoginClick -> doLogin(event)
        }
    }

    private fun doLogin(event: LoginViewEvent.OnLoginClick) {
        viewModelScope.launch {
            state.value = LoginViewState.Loading("Authenticating...")
            delay(3000)

            loginRepository
                .authenticate(
                    username = event.username,
                    password = event.password,
                )
                .onSuccess {
                    state.value = LoginViewState.Success
                }
                .onFailure {
                    state.value = LoginViewState.Error(it.message)
                }
        }
    }

}