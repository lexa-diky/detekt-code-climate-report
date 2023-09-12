package net.lexadily.tech.cq.detekt.codeclimate

import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Detektion
import io.gitlab.arturbosch.detekt.api.Finding
import io.gitlab.arturbosch.detekt.api.Location
import io.gitlab.arturbosch.detekt.api.RuleSetId
import io.gitlab.arturbosch.detekt.api.SeverityLevel
import net.lexadily.tech.cq.detekt.codeclimate.entity.Category
import net.lexadily.tech.cq.detekt.codeclimate.entity.Issue
import net.lexadily.tech.cq.detekt.codeclimate.entity.Positions
import net.lexadily.tech.cq.detekt.codeclimate.entity.Severity
import net.lexadily.tech.cq.detekt.codeclimate.entity.TextPointer

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

    private fun mapCategories(ruleSetId: String): List<Category> {
        return listOfNotNull(
            when (ruleSetId) {
                DETEKT_RSID_COMPLEXITY -> Category.Complexity
                DETEKT_RSID_PERFORMANCE -> Category.Performance
                DETEKT_RSID_POTENTIAL_BUGS -> Category.BugRisk
                else -> Category.Style
            }
        )
    }

    private fun mapLocation(dLocation: Location): net.lexadily.tech.cq.detekt.codeclimate.entity.Location {
        val path = dLocation.filePath.relativePath ?: dLocation.filePath.absolutePath

        return net.lexadily.tech.cq.detekt.codeclimate.entity.Location(
            path = path.toString(), positions = Positions(
                TextPointer(dLocation.source.line, dLocation.source.column)
            )
        )
    }

    private fun mapRemediationPoints(debt: Debt): Int {
        val hours = debt.days * HOURS_IN_DAY + debt.hours
        val minutes = hours * MINUTES_IN_HOUR + debt.mins
        return minutes * REMEDIATION_MULTIPLIER
    }

    companion object {

        private const val HOURS_IN_DAY = 24
        private const val MINUTES_IN_HOUR = 60
        private const val REMEDIATION_MULTIPLIER = 10_000

        private const val DETEKT_RSID_COMPLEXITY = "complexity"
        private const val DETEKT_RSID_PERFORMANCE = "performance"
        private const val DETEKT_RSID_POTENTIAL_BUGS = "potential-bugs"
    }
}
