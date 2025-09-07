import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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
        xml.required.set(true)
        html.required.set(true)
        sarif.required.set(true)
        md.required.set(true)
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
    jvmToolchain(21)
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.compilerOptions {
    freeCompilerArgs.set(listOf("-Xnested-type-aliases", "-Xskip-prerelease-check"))
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
