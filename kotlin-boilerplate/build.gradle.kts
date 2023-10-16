import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformJvmPlugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

buildscript {
    repositories {
        mavenCentral()
        google()
    }
}

plugins {
    java
    `java-library`
    jacoco
    groovy

    id(Plugin.spring_boot) version Versions.spring_boot apply false
    kotlin("jvm") version Versions.kotlin
    kotlin("kapt") version Versions.kotlin
    kotlin("plugin.spring") version Versions.kotlin apply false
    kotlin("plugin.noarg") version Versions.kotlin apply false
    kotlin("plugin.allopen") version Versions.kotlin apply false

}

allprojects {
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }
}

subprojects {
    apply {
        plugin<KotlinPlatformJvmPlugin>()
        plugin(Plugin.spring_boot)
//        plugin(Dependencies.Plugin.dependency_management)
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web:${Versions.spring_boot}")
        implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.60")
        implementation("org.jetbrains.kotlin:kotlin-reflect:1.3.60")

//        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
//        implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable:1.3.60")
    }

    project(":kotlin-api") {
        dependencies {
            implementation(project(":kotlin-domain"))
        }
    }
}

tasks.withType<BootJar> {
    enabled = false
}
