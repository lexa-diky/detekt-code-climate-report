package io.github.lexadiky.detekt.codeclimate

import io.github.lexadiky.detekt.codeclimate.entity.toJson
import io.gitlab.arturbosch.detekt.api.Detektion
import io.gitlab.arturbosch.detekt.api.OutputReport

class DetektCodeClimateReport : OutputReport() {

    private val mapper = DetektionToReportMapper()

    override val ending: String = "json"
    override val id: String = "code-climate"
    override val name: String = "code-climate"

    override fun render(detektion: Detektion): String {
        return mapper.map(detektion).toJson()
    }
}
