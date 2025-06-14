package com.mvi.modular.user.core


object UserConstants {

    internal const val KEY_USER_INFO = "user_info"

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // API TABLE ///////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    internal const val REGISTER = "api/v1/auth/register"
    internal const val VERIFY = "api/v1/auth/verify"
    internal const val GOOGLE_LOGIN = "api/v1/auth/third_party/verify"
    internal const val PROFILE = "api/v1/auth/profile"

}