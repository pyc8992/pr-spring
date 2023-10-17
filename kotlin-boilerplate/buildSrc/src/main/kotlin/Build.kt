object Versions {
  const val kotlin = "1.6.10"
  const val kotlinx = "1.6.0"
  const val kotlinx_logging = "1.2.0"
  const val spring_boot = "2.3.2.RELEASE"
  const val spring_cloud_dependency = "Hoxton.SR6"
  const val apache_poi = "5.0.0"
}

object Plugin {
  const val spring_boot = "org.springframework.boot"
  const val dependency_management = "io.spring.dependency-management"
  val spring_cloud_dependency = Dependency(
    group = "org.springframework.cloud",
    id = "spring-cloud-dependencies",
    version = Versions.spring_cloud_dependency
  )
  const val querydsl = "com.ewerk.gradle.plugins.querydsl"

  const val kotlin_spring = "org.jetbrains.kotlin.plugin.spring"
  const val kotlin_noarg = "org.jetbrains.kotlin.plugin.noarg"
  val kotlin_noarg_target_annotations = listOf(
    "javax.persistence.Entity",
    "javax.persistence.MappedSuperclass",
    "javax.persistence.Embeddable",
    "domain.utils.noarg.NoArg",
  )
}

object Library {
  val kotlin_stdlib = Dependency(group = "org.jetbrains.kotlin", id = "kotlin-stdlib-jdk8", version = Versions.kotlin)
  val kotlin_reflect = Dependency(group = "org.jetbrains.kotlin", id = "kotlin-reflect", version = Versions.kotlin)
  val kotlin_coroutine =
    Dependency(group = "org.jetbrains.kotlinx", id = "kotlinx-coroutine-core", version = Versions.kotlinx)
  val kotlin_logging =
    Dependency(group = "org.jetbrains.kotlinx", id = "kotlinx-logging", version = Versions.kotlinx_logging)
  val spring_boot_starter_data_jpa = Dependency(
    group = Plugin.spring_boot,
    id = "spring-boot-starter-data-jpa",
    version = Versions.spring_boot
  )
  val spring_boot_starter_jdbc = Dependency(
    group = Plugin.spring_boot,
    id = "spring-boot-starter-jdbc",
    version = Versions.spring_boot
  )
  val spring_boot_starter_batch = Dependency(
    group = Plugin.spring_boot,
    id = "spring-boot-starter-batch",
    version = Versions.spring_boot
  )

  val apache_poi = Dependency(
    group = "org.apache.poi",
    id = "poi",
    version = Versions.apache_poi
  )
  val mysql_connector = Dependency(group = "mysql", id = "mysql-connector-java", version = "8.0.33")
}

data class Dependency(val group: String, val id: String, val version: String) {

  constructor(group: String, id: String) : this(group = group, id = id, version = "")

  override fun toString(): String {
    if (version.isBlank()) {
      throw IllegalStateException("${group_id()} : version is empty")
    }
    return "${group_id()}:$version"
  }

  fun group_id(): String = "$group:$id"
}
