plugins {
    kotlin("jvm") version "2.1.10"
}

group = "com.kisu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    /* Test Dependencies */
    testImplementation(kotlin("test"))

    // JUnit 5
    testRuntimeOnly(libs.junit.jupiter.engine)
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.params)
    testImplementation(libs.jqwik)

    testImplementation(libs.kotest.assertions.core)
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(19)
}