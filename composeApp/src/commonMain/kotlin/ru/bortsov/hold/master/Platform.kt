package ru.bortsov.hold.master

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform