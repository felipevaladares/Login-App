package com.felpster.loginapp.presentation.login

import app.cash.turbine.test
import com.felpster.loginapp.commons.MainDispatcherRule
import com.felpster.loginapp.domain.LoginRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test


class LoginViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val repository: LoginRepository = mockk()
    private val viewModel = LoginViewModel(repository)

    @Test
    fun ` simulate success state`() = runTest {
        coEvery { repository.authenticate(any(), any()) } returns Result.success(Unit)
        viewModel.onEvent(LoginViewEvent.OnLoginClick("username", "12345"))

        viewModel.state.test {
            val firstItem = awaitItem()
            assertThat(firstItem).isEqualTo(LoginViewState.Success)
        }
    }

    @Test
    fun ` simulate error state`() = runTest {
        coEvery { repository.authenticate(any(), any()) } returns Result.failure(Exception("error"))
        viewModel.onEvent(LoginViewEvent.OnLoginClick("username", "1234asdasdad5"))

        viewModel.state.test {
            val firstItem = awaitItem()
            assertThat(firstItem).isEqualTo(LoginViewState.Error("error"))
        }
    }

}