package ru.bortsov.holdmaster.feature.photo.presentation.component

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.serialization.Serializable

@Serializable
data class PhotoState(
    val byteArray: ByteArray? = null,
) : InstanceKeeper.Instance {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as PhotoState

        if (byteArray != null) {
            if (other.byteArray == null) return false
            if (!byteArray.contentEquals(other.byteArray)) return false
        } else if (other.byteArray != null) return false

        return true
    }

    override fun hashCode(): Int {
        return byteArray?.contentHashCode() ?: 0
    }
}
