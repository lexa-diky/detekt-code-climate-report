# Detect ❤️ CodeClimate

![Project Type Detekt Plugin](https://img.shields.io/badge/detekt-plugin-blue)
![Github Actions Build Apl](https://img.shields.io/github/actions/workflow/status/lexa-diky/detekt-code-climate-report/build.yml)
![Stars](https://img.shields.io/github/stars/lexa-diky/detekt-code-climate-report)
![GitHub](https://img.shields.io/github/license/lexa-diky/detekt-code-climate-report)

Detekt report in [CodeClimate format](https://github.com/codeclimate/platform/blob/master/spec/analyzers/SPEC.md) used
by GitLab code quality reporting.

Shutout to [Cromefire's implementation](https://gitlab.com/cromefire/detekt-gitlab-report) for inspiration.

## Installation

1. Add dependency on the library in `detektPlugin` configuration

```kotlin
dependencies {
    add("detektPlugins", "io.github.lexa-diky:detekt-code-climate-report:0.1.0")
}
```

2. Add the report in detekt settings

```kotlin
tasks.withType<Detekt> {
    // (OPTIONAL) Required for proper relative file linking
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

To enable GitLab to read the generated report, please
follow [official guide](https://docs.gitlab.com/ee/ci/testing/code_quality.html#enable-code-quality).
