package com.mvi.modular.lang.domain.model


data class Lang(
    /**
     * As shortest ISO 639 code for language code
     * example: en (english), tr (turkish)
     */
    val languageCode: String,
    /**
     * As shortest ISO 3166-1 code for country code
     * example: US (United States)
     */
    val countryCode: String,

    val nativeTitle: String,
    val englishTitle: String,
    val direction: String,
    val isDefault: Boolean,
    val isEnable: Boolean = true
)
