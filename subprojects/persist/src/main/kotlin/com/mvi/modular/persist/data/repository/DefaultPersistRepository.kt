package com.mvi.modular.persist.data.repository

import android.content.SharedPreferences
import com.mvi.modular.persist.domain.repository.PersistRepository


internal class DefaultPersistRepository(
    private val sharedPreferences: SharedPreferences
) : PersistRepository {


    override fun storeString(key: String, value: String): Boolean {
        return sharedPreferences.edit().putString(key, value).commit()
    }

    override fun retrieveString(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    override fun storeBoolean(key: String, value: Boolean): Boolean {
        return sharedPreferences.edit().putBoolean(key, value).commit()
    }

    override fun retrieveBoolean(key: String): Boolean? {
        return if (!sharedPreferences.contains(key)) {
            null
        } else {
            sharedPreferences.getBoolean(key, false)
        }
    }

    override fun storeLong(key: String, value: Long): Boolean {
        return sharedPreferences.edit().putLong(key, value).commit()
    }

    override fun retrieveLong(key: String): Long? {
        return if (!sharedPreferences.contains(key)) {
            null
        } else {
            sharedPreferences.getLong(key, 0)
        }
    }

    override fun storeInt(key: String, value: Int): Boolean {
        return sharedPreferences.edit().putInt(key, value).commit()
    }

    override fun retrieveInt(key: String): Int? {
        return if (!sharedPreferences.contains(key)) {
            null
        } else {
            sharedPreferences.getInt(key, 0)
        }
    }

    override fun containsKey(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

    override fun removeSpecificKey(key: String): Boolean {
        return sharedPreferences.edit().remove(key).commit()
    }

    override fun removeAllKey() {
        sharedPreferences.edit().clear().apply()
    }
}