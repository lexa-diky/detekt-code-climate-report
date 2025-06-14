package io.github.lexadiky.detekt.codeclimate.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Positions(
    @SerialName("begin")
    val begin: TextPointer,
)
