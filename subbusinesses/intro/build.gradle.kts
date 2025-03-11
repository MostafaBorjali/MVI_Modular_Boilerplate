plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.compose)
}

android {
    namespace = config.versions.intro.get()
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
    implementation(projects.lang)
    implementation(projects.persist)
    implementation(projects.strings)
    implementation(projects.ui)
    implementation(projects.utils)

    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.ui)

    implementation(libs.koin)
}