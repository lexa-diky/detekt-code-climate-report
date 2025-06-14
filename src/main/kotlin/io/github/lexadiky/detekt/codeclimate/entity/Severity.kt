package io.github.lexadiky.detekt.codeclimate.entity

internal enum class Severity(private val value: String) {
    Info("info"),
    Major("major"),
    Critical("critical");

    internal fun toJson() = "\"$value\""
}
