package com.mvi.modular.persist.domain.repository


internal interface PersistRepository {

    /**
     * put string to SharedPreference
     */
    fun storeString(key: String, value: String): Boolean

    /**
     * retrieve string from SharedPreference
     */
    fun retrieveString(key: String): String?

    /**
     * put boolean to SharedPreference
     */
    fun storeBoolean(key: String, value: Boolean): Boolean

    /**
     * retrieve boolean from SharedPreference
     */
    fun retrieveBoolean(key: String): Boolean?

    /**
     * put long to SharedPreference
     */
    fun storeLong(key: String, value: Long): Boolean

    /**
     * retrieve long from SharedPreference
     */
    fun retrieveLong(key: String): Long?

    /**
     * put int to SharedPreference
     */
    fun storeInt(key: String, value: Int): Boolean

    /**
     * retrieve int from SharedPreference
     */
    fun retrieveInt(key: String): Int?

    /**
     * Check target key is exist or not
     */
    fun containsKey(key: String): Boolean

    /**
     * remove value of the specific key from SharedPreference
     */
    fun removeSpecificKey(key: String): Boolean

    /**
     * remove all values and keys from SharedPreference
     */
    fun removeAllKey()
}