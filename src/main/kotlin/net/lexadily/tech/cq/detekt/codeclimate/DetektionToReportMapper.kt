package net.lexadily.tech.cq.detekt.codeclimate

import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Detektion
import io.gitlab.arturbosch.detekt.api.Finding
import io.gitlab.arturbosch.detekt.api.RuleSetId
import io.gitlab.arturbosch.detekt.api.SeverityLevel
import net.lexadily.tech.cq.detekt.codeclimate.entity.Category
import net.lexadily.tech.cq.detekt.codeclimate.entity.Issue
import net.lexadily.tech.cq.detekt.codeclimate.entity.Location
import net.lexadily.tech.cq.detekt.codeclimate.entity.Positions
import net.lexadily.tech.cq.detekt.codeclimate.entity.Severity
import net.lexadily.tech.cq.detekt.codeclimate.entity.TextPointer
import io.gitlab.arturbosch.detekt.api.Location as DetektLocation

internal class DetektionToReportMapper {

    fun map(detektion: Detektion): List<Issue> = detektion.findings.flatMap { (ruleSetId, findings) ->
        findings.map { finding -> mapIssue(finding, ruleSetId) }
    }

    private fun mapIssue(finding: Finding, ruleSetId: RuleSetId) = Issue(
        checkName = finding.issue.id,
        description = finding.messageOrDescription(),
        categories = mapCategories(ruleSetId),
        location = mapLocation(finding.location),
        otherLocations = finding.references.map { mapLocation(it.location) },
        remediationPoints = mapRemediationPoints(finding.issue.debt),
        severity = mapSeverity(finding.severity),
        fingerprint = finding.signature,
    )

    private fun mapSeverity(level: SeverityLevel): Severity = when (level) {
        SeverityLevel.ERROR -> Severity.Critical
        SeverityLevel.WARNING -> Severity.Major
        SeverityLevel.INFO -> Severity.Info
    }

    private fun mapCategories(ruleSetId: RuleSetId): List<Category> {
        return listOfNotNull(
            when (ruleSetId) {
                DETEKT_RSID_COMPLEXITY -> Category.Complexity
                DETEKT_RSID_PERFORMANCE -> Category.Performance
                DETEKT_RSID_POTENTIAL_BUGS -> Category.BugRisk
                else -> Category.Style
            }
        )
    }

    private fun mapLocation(dLocation: DetektLocation): Location {
        val path = dLocation.filePath.relativePath ?: dLocation.filePath.absolutePath

        return Location(
            path = path.toString(),
            positions = Positions(
                begin = TextPointer(
                    line = dLocation.source.line,
                    column = dLocation.source.column
                )
            )
        )
    }

    private fun mapRemediationPoints(debt: Debt): Int {
        val hours = debt.days * HOURS_IN_DAY + debt.hours
        val minutes = hours * MINUTES_IN_HOUR + debt.mins
        return minutes * REMEDIATION_MULTIPLIER
    }

    companion object {

        private const val HOURS_IN_DAY: Int = 24
        private const val MINUTES_IN_HOUR: Int = 60
        private const val REMEDIATION_MULTIPLIER: Int = 10_000

        private const val DETEKT_RSID_COMPLEXITY: RuleSetId = "complexity"
        private const val DETEKT_RSID_PERFORMANCE: RuleSetId = "performance"
        private const val DETEKT_RSID_POTENTIAL_BUGS: RuleSetId = "potential-bugs"
    }
}
