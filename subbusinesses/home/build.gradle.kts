plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.compose)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    namespace = config.versions.home.get()
    compileSdk = config.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = config.versions.minSdk.get().toInt()

        testInstrumentationRunner = config.versions.testRunner.get()
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
    }

    composeOptions {
        kotlinCompilerExtensionVersion = config.versions.composeKotlinCompiler.get()
    }

    packaging {
        resources {
            excludes += config.versions.resourcesPackaging.get()
        }
    }
}

ConfigMod(project)
    .commonDependencies()
    .composeDependencies()
    .debugDependencies()
    .testDependencies()
    .androidTestDependencies()


dependencies {

    implementation(projects.base)
    implementation(projects.deeplink)

    implementation(projects.error)
    implementation(projects.integration)
    implementation(projects.lang)
    implementation(projects.movie)
    implementation(projects.navigation)
    implementation(projects.notification)
    implementation(projects.persist)
    implementation(projects.strings)
    implementation(projects.ui)
    implementation(projects.user)
    implementation(projects.utils)

    implementation(libs.androidx.constraintlayout)
    implementation(libs.koin)
    implementation(libs.coil)
}