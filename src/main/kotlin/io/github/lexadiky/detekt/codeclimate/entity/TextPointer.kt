package io.github.lexadiky.detekt.codeclimate.entity

internal data class TextPointer(
    val line: Int,
    val column: Int
) {
    fun toJson(): String = "{\"line\":$line,\"column\":$column}"
}
