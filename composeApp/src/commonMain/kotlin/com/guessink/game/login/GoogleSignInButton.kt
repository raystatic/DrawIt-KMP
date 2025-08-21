package com.guessink.game.login

import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

interface GoogleButtonClick {
    fun onSignInClicked()

    fun onSignOutClicked()
}

@Composable
fun GoogleSignInButton(
    modifier: Modifier = Modifier,
    onGoogleSignInResult: (GoogleAccount?) -> Unit,
) {
    val googleAuthProvider = koinInject<GoogleAuthProvider>()
    val googleAuthUiProvider = googleAuthProvider.getUiProvider()
    val coroutineScope = rememberCoroutineScope()

    val isLoggedIn = remember {
        mutableStateOf<Boolean>(false)
    }

    val uiContainerScope =
        remember {
            object : GoogleButtonClick {
                override fun onSignInClicked() {
                    coroutineScope.launch {
                        val googleUser = googleAuthUiProvider.signIn()
                        isLoggedIn.value = googleUser != null
                        onGoogleSignInResult(googleUser)
                    }
                }


                override fun onSignOutClicked() {
                    coroutineScope.launch {
                        isLoggedIn.value = false
                        googleAuthProvider.signOut()
                        onGoogleSignInResult(null)
                    }
                }
            }
        }
    OutlinedButton(
        modifier = modifier,
        onClick = {if (isLoggedIn.value) {
            uiContainerScope.onSignOutClicked()
        } else {
            uiContainerScope.onSignInClicked()
        } },
        content = {
            if (isLoggedIn.value.not()) {
                Text("Login")
            } else {
                Text("Logout")
            }

        }
    )
}
