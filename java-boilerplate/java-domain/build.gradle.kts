import org.springframework.boot.gradle.tasks.bundling.BootJar

//plugins {
//    id(Dependencies.Plugin.querydsl)
//}

//tasks.getByName("clean").dependsOn.add(tasks.getByName("xxx"))

tasks.withType<BootJar> {
    enabled = false
}

tasks.withType<Jar> {
    enabled = false
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter:${Versions.Plugin.spring_boot}")
}

//val querydslSrc = "$buildDir/generated/querydsl"
//
//querydsl {
//    jpa = true
//    querydslSourcesDir = querydslSrc
//}

//sourceSets {
//    main {
//        java.srcDirs("src/main/java", querydslSrc)
//    }
//}
