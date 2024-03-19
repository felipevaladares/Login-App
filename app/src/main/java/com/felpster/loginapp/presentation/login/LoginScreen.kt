package com.felpster.loginapp.presentation.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.felpster.loginapp.R
import com.felpster.loginapp.ui.components.ErrorLayout
import com.felpster.loginapp.ui.components.LoadingLayout
import com.felpster.loginapp.ui.theme.LoginAppTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun UsersScreen(
    viewState: LoginViewState,
    onEvent: (LoginViewEvent) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                title = { Text(text = stringResource(R.string.app_name)) },
            )
        },
    ) { padding ->
        when (viewState) {
            is LoginViewState.Success ->
                LoginContent(
                    onClick = onEvent,
                    modifier = Modifier.padding(padding),
                )

            is LoginViewState.Error ->
                ErrorLayout(
                    message = viewState.message,
                    modifier = Modifier.fillMaxSize().padding(padding),
                )

            is LoginViewState.Loading ->
                LoadingLayout(
                    message = viewState.message,
                    modifier = Modifier.fillMaxSize().padding(padding),
                )
        }
    }
}

@Composable
private fun LoginContent(
    modifier: Modifier = Modifier,
    onClick: (LoginViewEvent) -> Unit,
) {

}

object LoginContentLayoutTags {
    const val LOGIN_LAYOUT = "LoginContentLayoutTags_content"
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginAppTheme {
        UsersScreen(
            LoginViewState.Success,
        ) {}
    }
}

@Preview(showBackground = true)
@Composable
fun UsersScreenLoadingPreview() {
    LoginAppTheme {
        UsersScreen(
            LoginViewState.Loading("Authenticating..."),
        ) {}
    }
}

@Preview(showBackground = true)
@Composable
fun UsersScreenErrorPreview() {
    LoginAppTheme {
        UsersScreen(
            LoginViewState.Error("Oops, something went wrong!!"),
        ) {}
    }
}
