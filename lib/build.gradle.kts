import dev.detekt.gradle.Detekt
import org.gradle.api.publish.maven.MavenPublication
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    kotlin("jvm")
    `maven-publish`
    signing
    alias(libs.plugins.dokka)
    alias(libs.plugins.detekt)
}

group = providers.gradleProperty("LIB_GROUP").get()
version = providers.gradleProperty("VERSION_NAME").get()

val mavenCentralUsername = providers
    .gradleProperty("mavenCentralUsername")
    .orElse(providers.environmentVariable("MAVEN_CENTRAL_USERNAME"))
val mavenCentralPassword = providers
    .gradleProperty("mavenCentralPassword")
    .orElse(providers.environmentVariable("MAVEN_CENTRAL_PASSWORD"))
val signingKey = providers
    .gradleProperty("signingInMemoryKey")
    .orElse(providers.environmentVariable("SIGNING_KEY"))
val signingPassword = providers
    .gradleProperty("signingInMemoryKeyPassword")
    .orElse(providers.environmentVariable("SIGNING_PASSWORD"))

repositories {
    mavenCentral()
}

java {
    withSourcesJar()
    withJavadocJar()
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

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])

            artifactId = providers.gradleProperty("POM_ARTIFACT_ID").get()

            pom {
                name.set(providers.gradleProperty("POM_NAME"))
                description.set(providers.gradleProperty("POM_DESCRIPTION"))
                url.set(providers.gradleProperty("POM_URL"))
                inceptionYear.set(providers.gradleProperty("POM_INCEPTION_YEAR"))

                licenses {
                    license {
                        name.set(providers.gradleProperty("POM_LICENSE_NAME"))
                        url.set(providers.gradleProperty("POM_LICENSE_URL"))
                        distribution.set(providers.gradleProperty("POM_LICENSE_DISTRIBUTION"))
                    }
                }

                issueManagement {
                    system.set("GitHub Issues")
                    url.set("${providers.gradleProperty("POM_URL").get()}/issues")
                }

                developers {
                    developer {
                        id.set(providers.gradleProperty("POM_DEVELOPER_ID"))
                        name.set(providers.gradleProperty("POM_DEVELOPER_NAME"))
                        url.set(providers.gradleProperty("POM_DEVELOPER_URL"))
                    }
                }

                scm {
                    url.set(providers.gradleProperty("POM_SCM_URL"))
                    connection.set(providers.gradleProperty("POM_SCM_CONNECTION"))
                    developerConnection.set(providers.gradleProperty("POM_SCM_DEVELOPER_CONNECTION"))
                }
            }
        }
    }

    repositories {
        maven {
            name = "sonatype"
            url = uri("https://ossrh-staging-api.central.sonatype.com/service/local/staging/deploy/maven2/")
            credentials {
                username = mavenCentralUsername.orNull
                password = mavenCentralPassword.orNull
            }
        }
    }
}

signing {
    if (signingKey.isPresent && signingPassword.isPresent) {
        useInMemoryPgpKeys(signingKey.get(), signingPassword.get())
        sign(publishing.publications)
    }
}
