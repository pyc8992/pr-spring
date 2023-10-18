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

  id(Plugin.spring_boot) version Version.spring_boot apply false
  kotlin("jvm") version Version.kotlin
  kotlin("kapt") version Version.kotlin
  kotlin("plugin.spring") version Version.kotlin apply false
  kotlin("plugin.noarg") version Version.kotlin apply false
  kotlin("plugin.allopen") version Version.kotlin apply false

  id(Plugin.dependency_management) version Version.spring_dependency_management apply false
}

allprojects {
  tasks.withType<JavaCompile> {
    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"
  }
  tasks.withType<KotlinCompile> {
    sourceCompatibility = "1.8"
    kotlinOptions {
      jvmTarget = "1.8"
      freeCompilerArgs = listOf(
        "-Xjsr305=strict",
        "-Xjvm-default=enable",
        "-Xinline-classes",
        "-progressive"
      )
    }
  }
}

subprojects {
  repositories {
    mavenCentral()
  }

  apply {
    plugin<KotlinPlatformJvmPlugin>()
    plugin(Plugin.spring_boot)
    plugin(Plugin.kotlin_spring)
    plugin(Plugin.dependency_management)
    plugin("kotlin-kapt")
//        plugin(Dependencies.Plugin.dependency_management)
  }

  configure<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension> {
      imports {
          mavenBom(Plugin.spring_cloud_dependency.toString())
      }

      dependencies {
          dependency(Library.kotlin_stdlib.toString())
          dependency(Library.kotlin_reflect.toString())
          dependency(Library.kotlin_coroutine.toString())
//          dependency(Library.kotlin_logging.toString())
      }
  }

  dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:${Version.spring_boot}")
    implementation(Library.kotlin_stdlib.group_id())
    implementation(Library.kotlin_reflect.group_id())
    implementation(Library.kotlin_coroutine.group_id())
//    implementation(Library.kotlin_logging.group_id())

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
