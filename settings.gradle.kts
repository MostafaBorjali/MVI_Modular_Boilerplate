@file:Suppress("UnstableApiUsage")
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

rootProject.name = "MVI-Modular-Boilerplate"

val applicationList = ArrayList<String>()
val businessList = ArrayList<String>()
val projectList = ArrayList<String>()

// application module
applicationList.add("app")


// sub businesses
businessList.add("intro")

// sub projects
projectList.add("auth")
projectList.add("barcode")
projectList.add("base")
projectList.add("crashlytics")
projectList.add("deeplink")
projectList.add("error")
projectList.add("integration")
projectList.add("lang")
projectList.add("navigation")
projectList.add("network")
projectList.add("notification")
projectList.add("persist")
projectList.add("strings")
projectList.add("ui")
projectList.add("user")
projectList.add("utils")



for (element in applicationList) {
    include(":$element")
}

for (element in businessList) {
    include(":$element")
}

for (element in projectList) {
    include(":$element")
}


for (project in rootProject.children) {
    //
    // All application modules must be located in root directory
    //
    if (applicationList.contains(project.name)) {
        project.projectDir = file("$rootDir/${project.name}")
    }
    //
    // All business modules must be located in subbusinesses directory
    //
    else if (businessList.contains(project.name)) {
        project.projectDir = file("subbusinesses/${project.name}")
    }
    //
    // All project modules must be located in subprojects directory
    //
    else if (projectList.contains(project.name)) {
        project.projectDir = file("subprojects/${project.name}")
    }
}

 