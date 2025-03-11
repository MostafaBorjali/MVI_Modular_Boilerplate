package com.mvi.modular.lang.device.manager

import android.content.res.Resources
import androidx.core.os.ConfigurationCompat
import com.mvi.modular.lang.core.LocalizedContextResolver
import com.mvi.modular.lang.domain.manager.DeviceLanguageManager
import com.mvi.modular.lang.domain.model.Lang


internal class DefaultDeviceLanguageManager(
    private val localizedContextResolver: LocalizedContextResolver
) : DeviceLanguageManager {

    override fun getDeviceCurrentLang(): String? {
        val locales = ConfigurationCompat.getLocales(Resources.getSystem().configuration)
        return if (locales.isEmpty) null else locales[0]?.language
    }

    override fun getLocalizedStringResources(
        currentLanguage: Lang,
        resourceId: Int,
        vararg args: Any
    ): String {
        val context = localizedContextResolver.resolveContext(currentLanguage.languageCode)
        return if (args.isEmpty()) {
            context.getString(resourceId)
        } else {
            context.getString(resourceId, *args)
        }
    }
}