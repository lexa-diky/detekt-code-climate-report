package io.github.lexadiky.detekt.codeclimate

import io.gitlab.arturbosch.detekt.api.Config

internal data class CodeClimateReportConfig(
    val prettyPrint: Boolean,
) {

    companion object {

        fun from(config: Config): CodeClimateReportConfig = CodeClimateReportConfig(
            prettyPrint = config.valueOrDefault("prettyPrint", true)
        )
    }
}
