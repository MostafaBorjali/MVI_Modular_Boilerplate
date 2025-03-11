package com.mvi.modular.persist.service


interface PersistService {

    /**
     * Put target data (string) with specific key in SharedPreference
     *
     * @param key Specific key
     * @param value Target value to store
     * @return true if data store successfully, false otherwise
     */
    fun putString(key: String, value: String): Boolean

    /**
     * Retrieve data (string) from SharedPreference based on key
     *
     * @param key Specific key for target data
     * @return Data if key exist, null otherwise
     */
    fun getString(key: String): String?

    /**
     * Put target data (boolean) with specific key in SharedPreference
     *
     * @param key Specific key
     * @param value Target value to store
     * @return true if data store successfully, false otherwise
     */
    fun putBoolean(key: String, value: Boolean): Boolean

    /**
     * Retrieve data (boolean) from SharedPreference based on key
     *
     * @param key Specific key for target data
     * @return Data if key exist, null otherwise
     */
    fun getBoolean(key: String): Boolean?

    /**
     * Put target data (int64) with specific key in SharedPreference
     *
     * @param key Specific key
     * @param value Target value to store
     * @return true if data store successfully, false otherwise
     */
    fun putLong(key: String, value: Long): Boolean

    /**
     * Retrieve data (int64) from SharedPreference based on key
     *
     * @param key Specific key for target data
     * @return Data if key exist, null otherwise
     */
    fun getLong(key: String): Long?

    /**
     * Put target data (int32) with specific key in SharedPreference
     *
     * @param key Specific key
     * @param value Target value to store
     * @return true if data store successfully, false otherwise
     */
    fun putInt(key: String, value: Int): Boolean

    /**
     * Retrieve data (int32) from SharedPreference based on key
     *
     * @param key Specific key for target data
     * @return Data if key exist, null otherwise
     */
    fun getInt(key: String): Int?

    /**
     * Check target key in SharedPreference is exist or not
     *
     * @param key Specific key for check
     * @return true if key is exist, false otherwise
     */
    fun contains(key: String): Boolean

    /**
     * Remove target key and value from SharedPreference if exist
     *
     * @param key Target key
     * @return true if key exist and remove successfully, false otherwise
     */
    fun removeKey(key: String): Boolean

    /**
     * Remove all keys and values from SharedPreference (Default)
     * If you want wipe all SharedPreference data, you should use it
     */
    fun removeAll()
}