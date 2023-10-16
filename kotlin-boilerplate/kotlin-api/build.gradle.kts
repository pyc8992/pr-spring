
val providedRuntime: Configuration by configurations.creating

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":kotlin-domain"))
    implementation("org.springframework.boot:spring-boot-starter-web:${Versions.Plugin.spring_boot}")

    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
}
