
repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":kotlin-domain"))
    implementation(Library.spring_boot_starter_batch.toString())
}
