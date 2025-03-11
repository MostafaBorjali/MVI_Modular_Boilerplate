package com.mvi.modular.resolver

import android.app.Activity
import com.mvi.modular.MainActivity
import com.mvi.modular.integration.resolver.ActivityResolver


internal class DefaultActivityResolver : ActivityResolver {


    override fun resolve(id: Int): Class<out Activity> {
        return when (id) {
            ActivityResolver.ID.ACTIVITY_ID_MAIN -> MainActivity::class.java

            else -> throw IllegalArgumentException("Implementation not found for this id -> $id")
        }
    }
}