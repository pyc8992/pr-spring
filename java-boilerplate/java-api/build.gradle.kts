val providedRuntime: Configuration by configurations.creating

//val project_name: String by project
//
//tasks.withType<BootJar> {
//    archiveFileName.set("${project_name}-api.jar")
//}
repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":java-domain"))
    implementation("org.springframework.boot:spring-boot-starter-web:${Versions.Plugin.spring_boot}")

    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
}

//tasks.withType<ProcessResources> {
//
//}

//plugins {
//    java
//}
//
//version = "unspecified"
//
//repositories {
//    mavenCentral()
//}
//
//dependencies {
//    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
//    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
//}
//
//tasks.getByName<Test>("test") {
//    useJUnitPlatform()
//}
