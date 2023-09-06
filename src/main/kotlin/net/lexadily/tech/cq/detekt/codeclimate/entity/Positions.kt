package net.lexadily.tech.cq.detekt.codeclimate.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Positions(
    @SerialName("begin")
    val begin: Position,
) {
    @Serializable
    data class Position(
        @SerialName("line")
        val line: Int,
        @SerialName("column")
        val column: Int,
    )
}
