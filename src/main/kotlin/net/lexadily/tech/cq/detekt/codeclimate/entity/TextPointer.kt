package net.lexadily.tech.cq.detekt.codeclimate.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TextPointer(
    @SerialName("line")
    val line: Int,
    @SerialName("column")
    val column: Int,
)
