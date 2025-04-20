package com.mvi.modular.crashlytics.lifecycle

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.mvi.modular.integration.domain.extension.LifecycleExtension


class CrashlyticsModuleLifecycleExtension : LifecycleExtension {

    override fun onCreate() {
        val crashlytics = FirebaseCrashlytics.getInstance()
        if (crashlytics.didCrashOnPreviousExecution()) {
            crashlytics.sendUnsentReports()
        }
    }
}