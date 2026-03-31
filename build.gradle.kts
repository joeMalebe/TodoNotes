import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

buildscript {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
    }
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

application {
    mainClass = "com.example.todo.notes.TodonotesKt"
}

repositories {
    mavenCentral()
}

tasks {
    withType<KotlinJvmCompile>().configureEach {
        compilerOptions {
            allWarningsAsErrors.set(false)
            jvmTarget.set(JVM_21)
            freeCompilerArgs.add("-jvm-default=enable")
        }
    }

    withType<Test> {
        useJUnitPlatform()
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

dependencies {

    implementation(platform("org.http4k:http4k-bom:6.39.0.0"))
    implementation("org.http4k.pro:http4k-tools-hotreload")
    implementation("org.http4k:http4k-config")
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-format-kotlinx-serialization")
    implementation("org.http4k:http4k-tools-traffic-capture")
    testImplementation("org.http4k:http4k-testing-hamkrest")
    testImplementation("org.junit.jupiter:junit-jupiter-api:6.0.3")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:6.0.3")
    testImplementation("org.junit.platform:junit-platform-launcher:6.0.3")
}

