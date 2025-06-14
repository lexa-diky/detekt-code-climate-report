package io.github.lexadiky.detekt.codeclimate

internal fun String.escapeJson(): String =
    this.replace("\\", "\\\\")
        .replace("\"", "\\\"")
        .replace("\n", "\\n")
        .replace("\r", "\\r")
        .replace("\t", "\\t")

