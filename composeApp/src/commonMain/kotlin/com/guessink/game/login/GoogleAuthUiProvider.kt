package com.guessink.game.login

expect class GoogleAuthUiProvider {
    suspend fun signIn(): GoogleAccount?
}
