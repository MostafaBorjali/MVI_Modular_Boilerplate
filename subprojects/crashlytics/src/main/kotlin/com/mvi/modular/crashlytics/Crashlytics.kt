package com.mvi.modular.crashlytics

import com.google.firebase.crashlytics.FirebaseCrashlytics


/**
 * Application crashlytics object
 */
object Crashlytics {

    /**
     * Report [Throwable] to firebase crashlytics service
     */
    fun report(throwable: Throwable?) {
        if (throwable != null) {
            FirebaseCrashlytics.getInstance().recordException(throwable)
        }
    }

    /**
     * Log [message] to firebase crashlytics service
     */
    fun log(message: String) {
        FirebaseCrashlytics.getInstance().log(message)
    }

    /**
     * Set current user identifier
     */
    fun setUserId(userId: Long) {
        FirebaseCrashlytics.getInstance().setUserId(userId.toString())
    }
}