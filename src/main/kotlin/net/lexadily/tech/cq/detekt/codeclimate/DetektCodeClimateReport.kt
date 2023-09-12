package net.lexadily.tech.cq.detekt.codeclimate

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Detektion
import io.gitlab.arturbosch.detekt.api.Finding
import io.gitlab.arturbosch.detekt.api.OutputReport
import io.gitlab.arturbosch.detekt.api.RuleSetId
import io.gitlab.arturbosch.detekt.api.SeverityLevel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.lexadily.tech.cq.detekt.codeclimate.entity.Category
import net.lexadily.tech.cq.detekt.codeclimate.entity.Issue
import net.lexadily.tech.cq.detekt.codeclimate.entity.Location
import net.lexadily.tech.cq.detekt.codeclimate.entity.Positions
import net.lexadily.tech.cq.detekt.codeclimate.entity.Severity
import org.jetbrains.kotlin.utils.addToStdlib.applyIf
import io.gitlab.arturbosch.detekt.api.Location as DetektLocation

class DetektCodeClimateReport : OutputReport() {

    private lateinit var config: CodeClimateReportConfig

    private val mapper = DetektionToReportMapper()
    private val jsonEncoder by lazy {
        Json {
            prettyPrint = config.prettyPrint
            encodeDefaults = true
        }
    }

    override val ending: String = "codeclimate.json"
    override val id: String = "code-climate"
    override val name: String = "code-climate"

    override fun init(config: Config) {
        super.init(config)
        this.config = CodeClimateReportConfig.from(config)
    }

    override fun render(detektion: Detektion): String {
        require(::config.isInitialized) { "report configuration was not initialized" }

        return mapper.map(detektion)
            .let(jsonEncoder::encodeToString)
    }
}
