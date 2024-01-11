import io.gitlab.arturbosch.detekt.Detekt

plugins {
    kotlin("jvm") version libs.versions.kotlin
    kotlin("plugin.serialization") version libs.versions.kotlin
    id("io.gitlab.arturbosch.detekt") version libs.versions.detekt
    id("maven-publish")
    id("signing")
}

group = "net.lexadily.tech.cq"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(libs.detekt.api)
    implementation(libs.kotlinx.serialization)
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

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        register("maven", MavenPublication::class) {
            from(project.components["java"])
            groupId = "io.github.lexa-diky"
            artifactId = "detekt-code-climate"
            version = "0.0.4-SNAPSHOT"
        }
    }
}

publishing {
    repositories {
        maven {
            url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
            credentials {
                username = System.getenv("MAVEN_CENTRAL_USERNAME")
                password = System.getenv("MAVEN_CENTRAL_PASSWORD")
            }
        }
    }
}
