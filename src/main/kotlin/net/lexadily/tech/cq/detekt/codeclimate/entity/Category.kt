package net.lexadily.tech.cq.detekt.codeclimate.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal enum class Category {

    @SerialName("Complexity")
    Complexity,

    @SerialName("Performance")
    Performance,

    @SerialName("Bug Risk")
    BugRisk,

    @SerialName("Style")
    Style
}
