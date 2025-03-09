package com.ammotel.android.integration.resolver

import android.app.Activity


interface ActivityResolver {

    companion object ID {
        const val ACTIVITY_ID_MAIN = 1
    }

    /**
     * Get destination activity class
     *
     * @param id target activity id
     * @throws IllegalArgumentException whenever id not match
     *
     * @return target activity id
     */
    @Throws(IllegalArgumentException::class)
    fun resolve(id: Int): Class<out Activity>
}