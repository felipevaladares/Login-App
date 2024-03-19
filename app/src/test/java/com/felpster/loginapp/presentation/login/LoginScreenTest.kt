package com.felpster.loginapp.presentation.login

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import com.felpster.loginapp.commons.ComposeTest
import com.felpster.loginapp.ui.components.ErrorLayoutTags
import com.felpster.loginapp.ui.components.LoadingLayoutTags
import com.felpster.loginapp.ui.theme.LoginAppTheme
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LoginScreenTest : ComposeTest() {

    @Test
    fun `Ensure error layout is displayed`() {
        with(composeTestRule) {
            setContent {
                LoginAppTheme {
                    UsersScreen(viewState = LoginViewState.Error(null), onEvent = {})
                }
            }

            onNodeWithTag(ErrorLayoutTags.ERROR_LAYOUT).assertIsDisplayed()
        }
    }

    @Test
    fun `Ensure loading layout is displayed`() {
        with(composeTestRule) {
            setContent {
                LoginAppTheme {
                    UsersScreen(viewState = LoginViewState.Loading(null), onEvent = {})
                }
            }

            onNodeWithTag(LoadingLayoutTags.LOADING_LAYOUT).assertIsDisplayed()
        }
    }
}
