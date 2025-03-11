package com.mvi.modular.notification.registry

import com.mvi.modular.notification.domain.extension.NotificationExtension


interface NotificationExtensionRegistry {

    /**
     * Add target extension to notification subscribers list
     *
     * @param extension Target notification extension
     */
    fun subscribe(extension: NotificationExtension)

    /**
     * Remove target extension from notification subscribers list
     *
     * @param extension Target notification extension
     */
    fun unsubscribe(extension: NotificationExtension)
}