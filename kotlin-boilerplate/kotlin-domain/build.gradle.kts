import org.jetbrains.kotlin.noarg.gradle.NoArgExtension
import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

jar.enabled = true
bootJar.enabled = false

repositories {
    mavenCentral()
}

apply {
    plugin("kotlin-noarg")
    plugin("kotlin-allopen")
//    plugin("kotlin-jpa")
}

configure<NoArgExtension> {
    annotations(Dependencies.Plugin.kotlin_noarg_target_annotations)
}
