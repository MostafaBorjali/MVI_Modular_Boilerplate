package com.mvi.modular.deeplink.device.manager

import android.net.Uri
import com.mvi.modular.deeplink.core.DeeplinkConstants.DEEPLINK_HOME_PATH
import com.mvi.modular.deeplink.core.DeeplinkConstants.DEEPLINK_PROFILE_PATH
import com.mvi.modular.deeplink.core.DeeplinkConstants.DEEPLINK_SHOP_PATH
import com.mvi.modular.deeplink.domain.manager.DeeplinkManager
import com.mvi.modular.deeplink.domain.model.Deeplink
import com.mvi.modular.navigation.domain.model.Destination


internal class DefaultDeeplinkManager : DeeplinkManager {


    override suspend fun parse(value: String): Deeplink? {
        return try {
            val uri = Uri.parse(value) ?: return null
            localParse(uri)
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

    private fun localParse(uri: Uri): Deeplink? {
        val path = uri.path ?: return null

        val deeplink = when (path) {
            DEEPLINK_HOME_PATH -> {
                Deeplink(
                    destination = Destination.Home.Main,
                    subDestination = Destination.Home.ESim,
                    args = null,
                    needLogin = false,
                )
            }

            DEEPLINK_SHOP_PATH -> {
                Deeplink(
                    destination = Destination.Home.Main,
                    subDestination = Destination.Home.Explore,
                    args = null,
                    needLogin = false,
                )
            }

            DEEPLINK_PROFILE_PATH -> {
                Deeplink(
                    destination = Destination.Home.Main,
                    subDestination = Destination.Home.Profile,
                    args = null,
                    needLogin = false,
                )
            }

            else -> null

        } ?: return null

        return deeplink
    }
}