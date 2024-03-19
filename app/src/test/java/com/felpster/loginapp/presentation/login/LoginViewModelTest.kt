package com.felpster.loginapp.presentation.login

import app.cash.turbine.test
import com.felpster.loginapp.domain.LoginRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description


class LoginViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val repository: LoginRepository = mockk()
    private val viewModel = LoginViewModel(repository)

    @Test
    fun ` simulate success state`() = runTest {
        coEvery { repository.authenticate(any(), any()) } returns Result.success(Unit)
        viewModel.onEvent(LoginViewEvent.onLoginClick("username", "12345"))

        viewModel.state.test {
            val firstItem = awaitItem()
            assertThat(firstItem).isEqualTo(LoginViewState.Success)
        }
    }

    @Test
    fun ` simulate error state`() = runTest {
        coEvery { repository.authenticate(any(), any()) } returns Result.failure(Exception("error"))
        viewModel.onEvent(LoginViewEvent.onLoginClick("username", "1234asdasdad5"))

        viewModel.state.test {
            val firstItem = awaitItem()
            assertThat(firstItem).isEqualTo(LoginViewState.Error("error"))
        }
    }

}

@ExperimentalCoroutinesApi
class MainDispatcherRule(
    val dispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {

    override fun starting(description: Description) = Dispatchers.setMain(dispatcher)

    override fun finished(description: Description) = Dispatchers.resetMain()
}