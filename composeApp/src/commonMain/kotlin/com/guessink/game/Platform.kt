package com.guessink.game

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform