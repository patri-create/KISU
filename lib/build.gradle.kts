import dev.detekt.gradle.Detekt
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    kotlin("jvm")
    alias(libs.plugins.dokka)
    alias(libs.plugins.detekt)
}

group = "org.kisu"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

detekt {
    buildUponDefaultConfig = true
    config.setFrom(file("config/detekt/detekt.yml"))
}

tasks.withType<Detekt>().configureEach {
    reports {
        checkstyle.required.set(true)
        html.required.set(true)
        sarif.required.set(true)
        markdown.required.set(true)
    }
}

dependencies {
    detektPlugins(libs.detekt.cli)

    // Test Dependencies
    testImplementation(kotlin("test"))

    // Kotest
    testImplementation(libs.kotest)
    testImplementation(libs.kotest.assertions)
    testImplementation(libs.kotest.property)
}

kotlin {
    jvmToolchain(25)
    compilerOptions {
        apiVersion.set(KotlinVersion.KOTLIN_2_3)
        languageVersion.set(KotlinVersion.KOTLIN_2_3)
    }
}

dokka {
    moduleName.set("KISU")
    dokkaPublications {
        html {
            outputDirectory.set(layout.buildDirectory.dir("docs"))
        }
    }
    pluginsConfiguration {
        html {
            footerMessage.set("(c) Sefford & Patri-create 2025")
        }
    }
}
