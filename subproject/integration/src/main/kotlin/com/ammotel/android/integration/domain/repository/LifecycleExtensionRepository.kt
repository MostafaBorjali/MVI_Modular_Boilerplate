package com.ammotel.android.integration.domain.repository

import androidx.annotation.VisibleForTesting
import com.ammotel.android.integration.domain.extension.LifecycleExtension
import org.jetbrains.annotations.TestOnly


internal interface LifecycleExtensionRepository {

    /**
     * Add target extension to repository
     */
    fun addLifecycleExtension(extension: LifecycleExtension)

    /**
     * Remove target extension from repository
     */
    fun removeLifecycleExtension(extension: LifecycleExtension)

    /**
     * Get all extensions in new list
     */
    fun getAllLifecycleExtensions(): List<LifecycleExtension>

    /**
     * Clear all added extensions
     */
    @TestOnly
    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun clearAllLifecycleExtensions()
}