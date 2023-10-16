object Versions {
  object Plugin {
    const val kotlin = "1.3.60"
    const val spring_boot = "2.3.2.RELEASE"
  }

  object Library {
    const val apache_poi = "5.0.0"
  }
}

object Dependencies {
  object Plugin {
    const val spring_boot = "org.springframework.boot"
    const val dependency_management = "io.spring.dependency-management"
    const val querydsl = "com.ewerk.gradle.plugins.querydsl"

    const val kotlin_spring = "org.jetbrains.kotlin.plugin.spring"
    const val kotlin_noarg = "org.jetbrains.kotlin.plugin.noarg"
    val kotlin_noarg_target_annotations = listOf("javax.persistence.Entity",
      "javax.persistence.MappedSuperclass",
      "javax.persistence.Embeddable",
      "domain.utils.noarg.NoArg",
    )
  }

  object Library {
    val apache_poi = Dependency(
      group = "org.apache.poi",
      id = "poi",
      version = Versions.Library.apache_poi
    )
  }
}

data class Dependency(val group: String, val id: String, val version: String) {

  constructor(group: String, id: String) : this(group = group, id = id, version = "")

  override fun toString(): String {
    if (version.isBlank()) {
      throw IllegalStateException("${group_id()} : version is empty")
    }
    return "${group_id()}:$version"
  }

  fun group_id(): String  = "$group:$id"
}
