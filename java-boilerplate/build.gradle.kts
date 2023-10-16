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

    id(Dependencies.Plugin.spring_boot) version Versions.Plugin.spring_boot apply false

    kotlin("jvm") version Versions.Plugin.kotlin
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
        plugin(Dependencies.Plugin.spring_boot)
        plugin(Dependencies.Plugin.dependency_management)
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web:${Versions.Plugin.spring_boot}")
//        implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.60")
//        implementation("org.jetbrains.kotlin:kotlin-reflect:1.3.60")

//        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
//        implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable:1.3.60")
    }

    project(":java-api") {
        dependencies {
            implementation(project(":java-domain"))
        }
    }
}

tasks.withType<BootJar> {
    enabled = false
}
