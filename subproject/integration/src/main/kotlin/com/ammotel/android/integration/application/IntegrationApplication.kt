package com.ammotel.android.integration.application

import android.app.Application
import com.ammotel.android.integration.config.AppConfig
import com.ammotel.android.integration.domain.extension.LifecycleExtension
import com.ammotel.android.integration.domain.model.LifecycleStatus
import com.ammotel.android.integration.domain.usecase.NotifyApplicationLifecycleChangedUseCase
import com.ammotel.android.integration.registry.LifecycleExtensionRegistry
import org.koin.android.ext.android.inject


abstract class IntegrationApplication : Application() {

    private val activityLifecycleCallbacks: ActivityLifecycleCallbacks by inject()
    private val lifecycleExtensionRegistry: LifecycleExtensionRegistry by inject()
    private val notifyApplicationLifecycleChangedUseCase: NotifyApplicationLifecycleChangedUseCase by inject()


    override fun onCreate() {
        super.onCreate()
        /*
         * subscribe all lifecycle extensions candidate
         */
        lifecycleExtensions().forEach { extension ->
            lifecycleExtensionRegistry.subscribe(extension)
        }
        /*
         * register lifecycle callback
         */
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks)
        /*
         * notify all lifecycle extensions candidate that application created
         */
        notifyApplicationLifecycleChangedUseCase(LifecycleStatus.Create)
    }


    /**
     * Provide list of lifecycle extensions candidate
     */
    abstract fun lifecycleExtensions(): List<LifecycleExtension>

    /**
     *
     */
    abstract fun provideAppConfig(): AppConfig
}