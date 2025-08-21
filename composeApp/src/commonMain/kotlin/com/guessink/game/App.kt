package com.guessink.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.guessink.game.login.GoogleAccount
import com.guessink.game.login.GoogleSignInButton
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {

        val account = remember {
            mutableStateOf<GoogleAccount?>(null)
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            GoogleSignInButton(onGoogleSignInResult = { googleUser ->
                // send Google id token to your server
                account.value = googleUser
            })

            account.value?.let { user ->
                // Display user information
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(text = "Welcome, ${user.displayName}")
                }
            } ?: run {
                // Display a message when no user is signed in
            }
        }
    }
}
