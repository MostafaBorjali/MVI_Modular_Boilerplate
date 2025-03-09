package com.ammotel.android.integration.domain.model


data class ApplicationInfo(
    val applicationId: String,
    val versionCode: Int,
    val versionName: String,
    val debug: Boolean
)