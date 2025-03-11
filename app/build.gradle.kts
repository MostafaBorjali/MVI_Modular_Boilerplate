import com.google.firebase.crashlytics.buildtools.gradle.CrashlyticsExtension

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.compose)
    alias(libs.plugins.jetbrains.kotlin.parcelize)
    alias(libs.plugins.google.firebase)
    alias(libs.plugins.google.firebase.crashlytics)
}

android {
    namespace = config.versions.app.get()
    compileSdk = config.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.mvi.module"
        minSdk = config.versions.minSdk.get().toInt()
        targetSdk = config.versions.compileSdk.get().toInt()
        versionCode = config.versions.versionCode.get().toInt()
        versionName = config.versions.versionName.get()

        testInstrumentationRunner = config.versions.testRunner.get()
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile(config.versions.proguardOptimize.get()),
                config.versions.proguardRulse.get()
            )

            configure<CrashlyticsExtension> {
                mappingFileUploadEnabled = try {
                    if (project.hasProperty("uploadMapping")) {
                        project.property("uploadMapping").toString().toBoolean()
                    } else true
                } catch (_: Exception) {
                    true
                }
            }

            val testMode = try {
                if (project.hasProperty("testMode")) {
                    project.property("testMode").toString().toBoolean()
                } else false
            } catch (_: Exception) {
                false
            }
            buildConfigField("boolean", "TEST_MODE", testMode.toString())
        }

        debug {
            buildConfigField("boolean", "TEST_MODE", "true")

            configure<CrashlyticsExtension> {
                mappingFileUploadEnabled = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(config.versions.javaVersion.get())
        targetCompatibility = JavaVersion.toVersion(config.versions.javaVersion.get())
    }


    kotlinOptions {
        jvmTarget = config.versions.javaVersion.get()
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

ConfigMod(project)
    .commonDependencies()
    .composeDependencies()
    .debugDependencies()
    .testDependencies()
    .androidTestDependencies()

dependencies{
    //
    // sub-businesses
    //
    implementation(projects.intro)

    //
    // sub-projects
    //
    implementation(projects.auth)
    implementation(projects.base)
    implementation(projects.crashlytics)
    implementation(projects.deeplink)
    implementation(projects.error)
    implementation(projects.integration)
    implementation(projects.lang)
    implementation(projects.navigation)
    implementation(projects.network)
    implementation(projects.notification)
    implementation(projects.persist)
    implementation(projects.strings)
    implementation(projects.ui)
    implementation(projects.utils)

    //
    // libraries
    //
    implementation(libs.koin)
    implementation(libs.coil)

}