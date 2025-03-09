plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    implementation(gradleApi())
}

tasks.find { it.name == "testClasses" }?.let {
    it.enabled = false
}
