package net.lexadily.tech.cq.detekt.codeclimate.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Location(
    @SerialName("path")
    val path: String,
    @SerialName("positions")
    val positions: Positions,
)
