plugins {
    kotlin("jvm")
}

group = "org.kisu"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Test Dependencies
    testImplementation(kotlin("test"))

    // JUnit 5
    testRuntimeOnly(libs.junit.jupiter.engine)
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.params)
    testImplementation(libs.jqwik)

    testImplementation(libs.kotest.assertions.core)
}

kotlin {
    jvmToolchain(19)
}
