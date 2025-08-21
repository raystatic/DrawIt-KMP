package com.guessink.game

import androidx.compose.ui.window.ComposeUIViewController
import com.guessink.game.login.di.initKoin

fun MainViewController() =
    ComposeUIViewController(
        configure = {
            initKoin()
        },
    ) {
        App()
    }
