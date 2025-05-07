package ru.bortsov.holdmaster.core.utils

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.doOnDestroy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.serialization.KSerializer

public abstract class BaseComponent<T : Any>(
    componentContext: ComponentContext,
    initialState: T,
    private val serializer: KSerializer<T>
) : ComponentContext by componentContext {

    private val _stateKeeperLazy by lazy { stateKeeper }
    private val _instanceKeeperLazy by lazy { instanceKeeper }

    init { _stateKeeperLazy.register(STATE_KEY, serializer) { _handler.state.value } }

    protected val scope: CoroutineScope = componentCoroutineScope()

    protected val viewState: Value<T> get() = _handler.state

    private val _handler: Handler<T> = _instanceKeeperLazy.getOrCreate(INSTANCE_KEY) {
        Handler(_stateKeeperLazy.consume(STATE_KEY, serializer) ?: initialState)
    }

    protected fun updateState(update: T.() -> T): Unit = _handler.state.update { it.update() }

    private companion object {
        private const val INSTANCE_KEY = "instance_key"
        private const val STATE_KEY = "state_key"
    }
}

public fun ComponentContext.componentCoroutineScope(): CoroutineScope {
    val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    if (lifecycle.state != Lifecycle.State.DESTROYED) {
        lifecycle.doOnDestroy {
            scope.cancel()
        }
    } else {
        scope.cancel()
    }

    return scope
}

public class Handler<T : Any>(
    initVal: T
) : InstanceKeeper.Instance { public val state: MutableValue<T> = MutableValue(initVal) }