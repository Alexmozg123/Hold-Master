package ru.bortsov.holdmaster.core.base.di

import org.koin.core.Koin

public object Inject {

    private var _di: Koin? = null

    public val di: Koin
        get() = requireNotNull(_di)

    public fun createDependencies(tree: Koin) { _di = tree }

    public inline fun <reified T : Any> instance(): Lazy<T> = di.inject<T>()
}