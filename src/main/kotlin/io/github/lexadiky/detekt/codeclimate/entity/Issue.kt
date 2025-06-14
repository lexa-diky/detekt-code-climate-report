package io.github.lexadiky.detekt.codeclimate.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Issue(
    @SerialName("check_name")
    val checkName: String,
    @SerialName("description")
    val description: String,
    @SerialName("categories")
    val categories: List<Category>,
    @SerialName("location")
    val location: Location,
    @SerialName("other_locations")
    val otherLocations: List<Location>,
    @SerialName("remediation_points")
    val remediationPoints: Int,
    @SerialName("severity")
    val severity: Severity,
    @SerialName("fingerprint")
    val fingerprint: String
) {

    @SerialName("type")
    @Suppress("unused")
    val type: String = "issue"
}
