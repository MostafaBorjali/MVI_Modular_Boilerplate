package com.mvi.modular.utils.context

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import androidx.annotation.RawRes


/**
 * Restart application
 */
fun Context.restartApplication() {
    val intent = packageManager.getLaunchIntentForPackage(packageName)
    val componentName = intent?.component
    val mainIntent = Intent.makeRestartActivityTask(componentName)
    startActivity(mainIntent)
    Runtime.getRuntime().exit(0)
}

/**
 * Read raw resource file to string
 */
fun Resources.readRawString(@RawRes file: Int): String? {
    return try {
        openRawResource(file)
            .bufferedReader()
            .use { it.readText() }
    } catch (e: Exception) {
        return null
    }
}