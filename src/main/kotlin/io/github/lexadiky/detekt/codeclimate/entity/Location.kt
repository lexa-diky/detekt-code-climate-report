package io.github.lexadiky.detekt.codeclimate.entity

import io.github.lexadiky.detekt.codeclimate.escapeJson

internal data class Location(
    val path: String,
    val positions: Positions,
) {
    fun toJson(): String = "{\"path\":\"${path.escapeJson()}\",\"positions\":${positions.toJson()}}"
}
