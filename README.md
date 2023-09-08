# Detect CodeClimate

![Project Type Detekt Plugin](https://img.shields.io/badge/detekt-plugin-blue)
![Github Actions Build Apl](https://img.shields.io/github/actions/workflow/status/lexa-diky/detekt-code-climate-report/build.yml)
![Stars](https://img.shields.io/github/stars/lexa-diky/detekt-code-climate-report)
![GitHub](https://img.shields.io/github/license/lexa-diky/detekt-code-climate-report)

Detekt report in [CodeClimate format](https://github.com/codeclimate/platform/blob/master/spec/analyzers/SPEC.md)

## Installation

1. Add dependency on library in `detektPlugin` configuration

```kotlin
dependencies {
    add("detektPlugins", "net.lexadily.tech.cq:detekt-code-climate:<latest-version>")
}
```

2. Add report in detekt settings

```kotlin
tasks.detekt {
    // recommended for better report generation
    basePath = project.layout.projectDirectory.asFile.absolutePath

    reports {
        custom {
            reportId = "code-climate"
            outputLocation.set("<location>")
        }
    }
}
```

## GitLab integration

To enable GitLab to read generated report, please
follow [official guide](https://docs.gitlab.com/ee/ci/testing/code_quality.html#enable-code-quality).
