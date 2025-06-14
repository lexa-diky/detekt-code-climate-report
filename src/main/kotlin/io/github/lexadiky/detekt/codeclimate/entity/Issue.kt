package io.github.lexadiky.detekt.codeclimate.entity

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
            "\"check_name\":\"$checkName\"," +
            "\"description\":\"$description\"," +
            "\"categories\":[${categories.joinToString(",") { it.toJson() }}]," +
            "\"location\":${location.toJson()}," +
            "\"other_locations\":[${otherLocations.joinToString(",") { it.toJson() }}]," +
            "\"remediation_points\":$remediationPoints," +
            "\"severity\":\"$severity\"," +
            "\"fingerprint\":\"$fingerprint\"," +
            "\"type\":\"$type\"" +
            "}"
}

internal fun List<Issue>.toJson(): String =
    "[${joinToString(",") { it.toJson() }}]"
