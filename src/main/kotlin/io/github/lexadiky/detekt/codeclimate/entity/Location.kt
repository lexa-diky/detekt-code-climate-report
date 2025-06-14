package io.github.lexadiky.detekt.codeclimate.entity

internal data class Location(
    val path: String,
    val positions: Positions,
) {
    fun toJson(): String = "{\"path\":\"$path\",\"positions\":${positions.toJson()}}"
}
