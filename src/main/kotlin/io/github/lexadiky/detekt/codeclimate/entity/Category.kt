package io.github.lexadiky.detekt.codeclimate.entity

internal enum class Category(private val value: String) {
    BugRisk("Bug Risk"),
    Clarity("Clarity"),
    Complexity("Complexity"),
    Performance("Performance"),
    Style("Style");

    internal fun toJson() = "\"$value\""
}
