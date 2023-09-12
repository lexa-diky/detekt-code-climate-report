package net.lexadily.tech.cq.detekt.codeclimate

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Detektion
import io.gitlab.arturbosch.detekt.api.OutputReport
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class DetektCodeClimateReport : OutputReport() {

    private lateinit var config: CodeClimateReportConfig

    private val mapper = DetektionToReportMapper()
    private val jsonEncoder by lazy {
        Json {
            prettyPrint = config.prettyPrint
            encodeDefaults = true
        }
    }

    override val ending: String = "json"
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
