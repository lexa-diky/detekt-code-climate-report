package io.github.lexadiky.detekt.codeclimate.entity

internal data class Positions(
    val begin: TextPointer,
) {
    fun toJson(): String = "{\"begin\": ${begin.toJson()}}"
}
