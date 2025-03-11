package com.mvi.modular.notification.lifecycle

import android.content.Context
import com.mvi.modular.integration.domain.extension.LifecycleExtension
import com.google.firebase.FirebaseApp
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class NotificationModuleLifecycleExtension : LifecycleExtension, KoinComponent {


    override fun onCreate() {
        val context: Context by inject()
        FirebaseApp.initializeApp(context)
    }
}