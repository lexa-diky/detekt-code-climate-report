package io.github.lexadiky.detekt.codeclimate.entity

internal enum class Category(private val value: String) {
    Complexity("Complexity"),
    Performance("Performance"),
    BugRisk("Bug Risk"),
    Style("Style");

    internal fun toJson() = "\"$value\""
}
