val jacocoVersion = libs.versions.jacoco.get()

plugins {
    jacoco
}

repositories {
    mavenCentral()
}

subprojects {
    apply(plugin = "jacoco")

    extensions.configure<JacocoPluginExtension> {
        toolVersion = jacocoVersion
    }

    repositories {
        mavenCentral()
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
        finalizedBy("jacocoTestReport")
    }

    tasks.withType<JacocoReport>().configureEach {
        dependsOn("test")

        reports {
            xml.required.set(true)
            html.required.set(true)
        }
    }
}