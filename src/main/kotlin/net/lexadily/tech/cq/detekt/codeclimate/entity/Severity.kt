package net.lexadily.tech.cq.detekt.codeclimate.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal enum class Severity {
    @SerialName("info")
    Info,

    @SerialName("major")
    Major,

    @SerialName("critical")
    Critical,
}
