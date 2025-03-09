import org.gradle.api.initialization.resolve.RepositoriesMode.*
import java.net.URI

pluginManagement {
    repositories {
        google()
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("config") {
            from(files("$rootDir/gradle/config.versions.toml"))
        }
    }
    repositoriesMode.set(FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = URI("https://maven.google.com")
        }
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "MVI_Modular_Boilerplate"
include(":app")
 