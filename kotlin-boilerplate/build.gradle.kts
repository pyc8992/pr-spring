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

//    configure<DependencyManagementExtension> {
////        imports {
////            mavenBom(Plugin.spring_cloud_dependency.toString())
////        }
//
//        dependencies {
//            dependency(Library.kotlin_stdlib.toString())
//            dependency(Library.kotlin_reflect.toString())
//            dependency(Library.kotlin_coroutine.toString())
//            dependency(Library.kotlin_logging.toString())
//        }
//    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web:${Versions.spring_boot}")
        implementation(Library.kotlin_stdlib.toString())
        implementation(Library.kotlin_reflect.toString())
//        implementation(Library.kotlin_coroutine.toString())
//        implementation(Library.kotlin_logging.toString())
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
