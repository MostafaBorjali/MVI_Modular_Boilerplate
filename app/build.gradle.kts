plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.compose)
}

android {
    namespace = config.versions.app.get()
    compileSdk = config.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.example.mvi"
        minSdk = config.versions.minSdk.get().toInt()
        targetSdk = config.versions.compileSdk.get().toInt()
        versionCode = config.versions.versionCode.get().toInt()
        versionName = config.versions.versionName.get()

        testInstrumentationRunner = config.versions.testRunner.get()
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile(config.versions.proguardOptimize.get()),
                config.versions.proguardRulse.get()
            )
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
