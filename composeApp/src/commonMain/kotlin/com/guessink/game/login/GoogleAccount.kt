package com.guessink.game.login

data class GoogleAccount(
    val token: String,
    val displayName: String = "",
    val profileImageUrl: String? = null
)
