package ru.bortsov.holdmaster.feature.photo.presentation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.coroutines.launch
import ru.bortsov.holdmaster.core.utils.Handler
import ru.bortsov.holdmaster.core.utils.componentCoroutineScope
import ru.bortsov.holdmaster.feature.photo.api.PhotoRepository

internal class PhotoComponent(
    componentContext: ComponentContext,
    private val repository: PhotoRepository,
) : Photo, ComponentContext by componentContext {

    private val _stateKeeperLazy by lazy { stateKeeper }
    private val _instanceKeeperLazy by lazy { instanceKeeper }
    private val componentScope = componentCoroutineScope()

    private val _state = _instanceKeeperLazy.getOrCreate(INSTANCE_KEY) {
        Handler(_stateKeeperLazy.consume(STATE_KEY, PhotoState.serializer()) ?: PhotoState())
    }

    override val state: Value<PhotoState>
        get() = _state.state

    init { _stateKeeperLazy.register(STATE_KEY, PhotoState.serializer()) { _state.state.value } }

    override fun onTakePhotoClick() {
        componentScope.launch { repository.takeAPhoto() }
    }

    class Factory(
        private val repository: PhotoRepository,
    ) : Photo.Factory {
        override fun invoke(componentContext: ComponentContext): Photo {
            return PhotoComponent(
                componentContext = componentContext,
                repository = repository
            )
        }
    }

    private companion object {
        private const val INSTANCE_KEY = "instance_key"
        private const val STATE_KEY = "state_key"
    }
}