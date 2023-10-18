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
    plugin("kotlin-jpa")
}

configure<NoArgExtension> {
    annotations(Plugin.kotlin_noarg_target_annotations)
}

dependencies {
    implementation(Library.spring_boot_starter_data_jpa.toString())
    implementation(Library.spring_boot_starter_jdbc.toString())

    implementation(Library.mysql_connector.toString())
    implementation(Library.apache_poi.toString())

    // queryDsl
    implementation(Library.querydsl_jpa.toString())
    kapt(Library.querydsl_apt.toString() + ":jpa")
}
