import io.gitlab.arturbosch.detekt.Detekt

plugins {
    kotlin("jvm") version "1.9.10"
    kotlin("plugin.serialization") version "1.9.10"
    id("io.gitlab.arturbosch.detekt") version "1.23.1"
    id("maven-publish")
    id("signing")
}

group = "net.lexadily.tech.cq"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("io.gitlab.arturbosch.detekt:detekt-api:1.23.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
}

kotlin {
    jvmToolchain(8)
}

tasks.register<Detekt>("detektCheck") {
    allRules = true
    source(project.layout.projectDirectory.dir("src"))
    reports {
        txt.required.set(true)
        sarif.required.set(true)
    }
}
