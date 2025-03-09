package com.ammotel.android.integration.service


interface LifecycleService {

    /**
     * Check weather is application foreground or not
     *
     * @return true if application is foreground, false otherwise
     */
    fun isApplicationForeground(): Boolean
}