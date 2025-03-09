package com.ammotel.android.base.functional


open class Event<out T>(private val content: T) {

    /**
     * Check this event is handled or not
     */
    var handled = false
        private set

    /**
     * Returns the event and mark this event as handled
     */
    fun consume(): T {
        handled = true
        return content
    }

    /**
     * Return the event
     */
    fun peek(): T {
        return content
    }
}