plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.compose)
    // alias(libs.plugins.jetbrains.kotlin.parcelize)
}

android {
    namespace = config.versions.movie.get()
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
        aidl = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = config.versions.composeKotlinCompiler.get()
    }

    sourceSets {
        getByName("main") {
            aidl.srcDir("src/main/aidl")
            java.srcDirs("src/main/java", "src/main/aidl")
            kotlin.srcDirs("src/main/kotlin", "src/main/aidl")
        }
    }

    packaging {
        resources {
            excludes += config.versions.resourcesPackaging.get()
        }
    }
}

ConfigMod(project)
    .commonDependencies()
    .testDependencies()
    .androidTestDependencies()


dependencies {
    implementation(projects.base)
    implementation(projects.error)
    implementation(projects.integration)
    implementation(projects.network)

    implementation(libs.koin)
}