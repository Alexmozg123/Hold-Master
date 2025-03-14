package ru.bortsov.holdmaster.composeapp

import org.koin.core.Koin
import ru.bortsov.holdmaster.composeapp.decompose.Root

object Inject {

    private var _di: Koin? = null

    val di: Koin
        get() = requireNotNull(_di)

    fun createDependencies(tree: Koin) { _di = tree }

    inline fun <reified T : Any> instance(): Lazy<T> = lazy { di.get<T>() }

    /**
     * Важно: убираем lazy, inline и reified
     * из-за особенностей компиляции Kotlin в Objective-C
     */
    fun getRootFactory(): Root.Factory { return di.get<Root.Factory>() }
}