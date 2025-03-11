// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.jetbrains.kotlin.compose) apply false
    alias(libs.plugins.jetbrains.kotlin.parcelize) apply false
    alias(libs.plugins.google.firebase) apply false
    alias(libs.plugins.google.firebase.crashlytics) apply false
}

//
// add this task dummy to ignore buildSrc error
//
tasks.register("testClasses")

//
//
//
tasks.register("clean") {
    delete(rootProject.layout.buildDirectory)
}