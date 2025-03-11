package com.mvi.modular.deeplink.device.handler

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.mvi.modular.deeplink.domain.manager.DeeplinkManager
import com.mvi.modular.deeplink.domain.usecase.SetPendingDeeplinkUseCase
import com.mvi.modular.integration.marker.ApplicationStarterMarker
import com.mvi.modular.integration.resolver.ActivityResolver
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


@ApplicationStarterMarker
class DeeplinkHandler : ComponentActivity() {


    private val activityResolver: ActivityResolver by inject()
    private val deeplinkManager: DeeplinkManager by inject()
    private val setPendingDeeplinkUseCase: SetPendingDeeplinkUseCase by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        checkDeeplinkRoute(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        checkDeeplinkRoute(intent)
    }

    private fun checkDeeplinkRoute(intent: Intent?) {
        if (intent == null) {
            goToHome()
            return
        }

        val data = intent.data
        if (data == null) {
            goToHome()
            return
        }

        lifecycleScope.launch {
            val deeplink = deeplinkManager.parse(data.toString())
            if (deeplink != null) setPendingDeeplinkUseCase(deeplink)
            goToHome()
        }
    }

    private fun goToHome() {
        val homeIntent = Intent(
            this.applicationContext,
            activityResolver.resolve(ActivityResolver.ACTIVITY_ID_MAIN)
        ).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(homeIntent)
        this.finish()
    }
}