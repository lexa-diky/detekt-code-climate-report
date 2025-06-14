# Detect ❤️ CodeClimate

![Project Type Detekt Plugin](https://img.shields.io/badge/detekt-plugin-blue)
![Github Actions Build Apl](https://img.shields.io/github/actions/workflow/status/lexa-diky/detekt-code-climate-report/build.yml)
![Stars](https://img.shields.io/github/stars/lexa-diky/detekt-code-climate-report)
![GitHub](https://img.shields.io/github/license/lexa-diky/detekt-code-climate-report)

Detekt report in [CodeClimate format](https://github.com/codeclimate/platform/blob/master/spec/analyzers/SPEC.md)

Shutout to [Cromefire's implementation](https://gitlab.com/cromefire/detekt-gitlab-report) for inspiration.

## Installation

1. Add dependency on the library in `detektPlugin` configuration

```kotlin
dependencies {
    add("detektPlugins", "io.github.lexa-diky:detekt-code-climate:0.0.2-SNAPSHOT")
}
```

2. Add the report in detekt settings

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

## Configuration

`prettyPrint` (default: true) - if true, output json will be properly formatted and human-readable

## GitLab integration

To enable GitLab to read the generated report, please
follow [official guide](https://docs.gitlab.com/ee/ci/testing/code_quality.html#enable-code-quality).
