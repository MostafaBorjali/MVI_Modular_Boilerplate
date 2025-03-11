package com.mvi.modular.crashlytics.lifecycle

import com.mvi.modular.integration.domain.extension.LifecycleExtension
import com.google.firebase.crashlytics.FirebaseCrashlytics


class CrashlyticsModuleLifecycleExtension : LifecycleExtension {

    override fun onCreate() {
        val crashlytics = FirebaseCrashlytics.getInstance()
        if (crashlytics.didCrashOnPreviousExecution()) {
            crashlytics.sendUnsentReports()
        }
    }
}