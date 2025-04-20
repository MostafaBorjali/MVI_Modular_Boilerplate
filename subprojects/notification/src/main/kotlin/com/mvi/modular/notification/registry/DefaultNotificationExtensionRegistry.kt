package com.mvi.modular.notification.registry

import com.mvi.modular.notification.domain.extension.NotificationExtension


internal class DefaultNotificationExtensionRegistry : NotificationExtensionRegistry {


    override fun subscribe(extension: NotificationExtension) {
    }

    override fun unsubscribe(extension: NotificationExtension) {
    }
}