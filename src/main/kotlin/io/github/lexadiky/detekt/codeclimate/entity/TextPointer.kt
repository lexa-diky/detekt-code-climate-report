package io.github.lexadiky.detekt.codeclimate.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class TextPointer(
    @SerialName("line")
    val line: Int,
    @SerialName("column")
    val column: Int,
)
