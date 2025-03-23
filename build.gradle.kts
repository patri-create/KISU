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
    testImplementation(libs.kotest.assertions.core)
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(19)
}