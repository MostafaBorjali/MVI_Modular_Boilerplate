package com.mvi.modular.integration.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.mvi.modular.integration.domain.model.LifecycleStatus
import com.mvi.modular.integration.domain.usecase.NotifyApplicationLifecycleChangedUseCase
import com.mvi.modular.integration.domain.usecase.UpdateLifecycleStatusUseCase
import com.mvi.modular.integration.marker.ApplicationStarterMarker


internal class IntegrationActivityLifecycleCallbacks(
    private val updateLifecycleStatusUseCase: UpdateLifecycleStatusUseCase,
    private val notifyApplicationLifecycleChangedUseCase: NotifyApplicationLifecycleChangedUseCase
) : Application.ActivityLifecycleCallbacks {

    private var activityReferencesCount = 0
    private var activityChangingConfigurations = false


    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        if (activity::class.java.isAnnotationPresent(ApplicationStarterMarker::class.java)) {
            updateLifecycleStatusUseCase(true)
        }
    }

    override fun onActivityStarted(activity: Activity) {
        if (++activityReferencesCount == 1 && !activityChangingConfigurations) {
            updateLifecycleStatusUseCase(true)
            notifyApplicationLifecycleChangedUseCase(LifecycleStatus.Foreground)
        }
    }

    override fun onActivityStopped(activity: Activity) {
        activityChangingConfigurations = activity.isChangingConfigurations
        if (--activityReferencesCount == 0 && !activityChangingConfigurations) {
            updateLifecycleStatusUseCase(false)
            notifyApplicationLifecycleChangedUseCase(LifecycleStatus.Background)
        }
    }


    /*
     * not used functions
     */

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }
}