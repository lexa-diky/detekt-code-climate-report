import io.gitlab.arturbosch.detekt.Detekt
import com.vanniktech.maven.publish.SonatypeHost

plugins {
    kotlin("jvm") version "2.2.0"

    id("com.vanniktech.maven.publish") version "0.32.0"
    id("io.gitlab.arturbosch.detekt") version "1.23.8"
    id("org.jetbrains.kotlinx.binary-compatibility-validator") version "0.17.0"
    id("com.star-zero.gradle.githook") version "1.2.1"
}

group = "io.github.lexa-diky"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("io.gitlab.arturbosch.detekt:detekt-api:1.23.8")
}

kotlin {
    jvmToolchain(17)
}

tasks.withType<Detekt> {
    allRules = true
    source(project.layout.projectDirectory.dir("src"))
    reports {
        txt.required.set(true)
        sarif.required.set(true)
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    reports {
        junitXml.required = true
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()
    coordinates(
        groupId = group.toString(),
        artifactId = "detekt-code-climate-report",
        version = version.toString()
    )

    pom {
        name = "detekt-code-climate-report"
        description = "Detekt report generator for GitLab & CodeClimate"
        inceptionYear = "2023"
        url = "https://github.com/lexa-diky/detekt-code-climate-report"

        licenses {
            license {
                name = "The Unlicense"
                url = "https://unlicense.org/"
                distribution = "repo"
            }
        }
        developers {
            developer {
                id = "lexa-diky"
                name = "Aleksei Iakovlev"
                url = "https://github.com/lexa-diky"
            }
        }
        scm {
            url = "https://github.com/lexa-diky/detekt-code-climate-report"
        }
    }
}
