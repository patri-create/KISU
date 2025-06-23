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

        // Dynamically scale the parallelism of Kotest to the maximum cores supported by the hardware
        val cores = Runtime.getRuntime().availableProcessors()
        systemProperty("kotest.framework.parallelism", cores.toString())
        systemProperty("kotest.framework.concurrent.specs", "true")
        logger.info("Configured Kotest parallelism to $cores threads")

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
