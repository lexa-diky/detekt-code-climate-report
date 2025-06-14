package io.github.lexadiky.detekt.codeclimate.entity

import io.github.lexadiky.detekt.codeclimate.escapeJson

internal data class Issue(
    val checkName: String,
    val description: String,
    val categories: List<Category>,
    val location: Location,
    val otherLocations: List<Location>,
    val remediationPoints: Int,
    val severity: Severity,
    val fingerprint: String
) {
    @Suppress("unused")
    val type: String = "issue"

    fun toJson(): String = "{" +
            "\"check_name\":\"${checkName.escapeJson()}\"," +
            "\"description\":\"${description.escapeJson()}\"," +
            "\"categories\":[${categories.joinToString(",") { it.toJson() }}]," +
            "\"location\":${location.toJson()}," +
            "\"other_locations\":[${otherLocations.joinToString(",") { it.toJson() }}]," +
            "\"remediation_points\":$remediationPoints," +
            "\"severity\":${severity.toJson()}," +
            "\"fingerprint\":\"${fingerprint.escapeJson()}\"," +
            "\"type\":\"${type.escapeJson()}\"" +
            "}"
}

internal fun List<Issue>.toJson(): String =
    "[${joinToString(",") { it.toJson() }}]"
