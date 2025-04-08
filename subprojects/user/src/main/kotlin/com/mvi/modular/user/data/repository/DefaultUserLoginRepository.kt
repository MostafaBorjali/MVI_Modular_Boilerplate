package com.mvi.modular.user.data.repository

import com.mvi.modular.auth.domain.model.AuthToken
import com.mvi.modular.auth.domain.model.TokenType
import com.mvi.modular.auth.service.AuthService
import com.mvi.modular.base.functional.Either
import com.mvi.modular.crashlytics.Crashlytics
import com.mvi.modular.error.domain.model.Error
import com.mvi.modular.persist.service.PersistService
import com.mvi.modular.user.core.UserConstants.KEY_USER_INFO
import com.mvi.modular.user.data.datasource.remote.UserLoginRemoteDataSource
import com.mvi.modular.user.data.mapper.toJson
import com.mvi.modular.user.data.mapper.toUserRegisterInfo
import com.mvi.modular.user.domain.model.UserRegisterInfo
import com.mvi.modular.user.domain.repository.UserLoginRepository


internal class DefaultUserLoginRepository(
    private val userLoginRemoteDataSource: UserLoginRemoteDataSource,
    private val authService: AuthService,
    private val persistService: PersistService,
) : UserLoginRepository {


    override suspend fun register(email: String): Either<UserRegisterInfo, Error> {
        return when (val response = userLoginRemoteDataSource.register(email)) {
            is Either.Success -> {
                Either.Success(response.data.toUserRegisterInfo())
            }

            is Either.Error -> {
                Either.Error(response.error)
            }
        }
    }

    override suspend fun verify(
        email: String,
        verificationCode: String
    ): Either<Boolean, Error> {
        return when (val response = userLoginRemoteDataSource.verify(email, verificationCode)) {
            is Either.Success -> {
                val tokenStored = authService.insert(
                    AuthToken(
                        value = response.data.token,
                        expireAt = response.data.expiredAt,
                    ),
                    TokenType.AccessToken
                )

                val userStored = persistService.putString(
                    KEY_USER_INFO,
                    response.data.user.toJson()
                )

                Crashlytics.setUserId(response.data.user.id)

                Either.Success(tokenStored and userStored)
            }

            is Either.Error -> {
                Either.Error(response.error)
            }
        }
    }

    override suspend fun google(token: String): Either<Boolean, Error> {
        return when (val response = userLoginRemoteDataSource.google(token)) {
            is Either.Success -> {
                val tokenStored = authService.insert(
                    AuthToken(
                        value = response.data.token,
                        expireAt = response.data.expiredAt,
                    ),
                    TokenType.AccessToken
                )

                val userStored = persistService.putString(
                    KEY_USER_INFO,
                    response.data.user.toJson()
                )

                Crashlytics.setUserId(response.data.user.id)

                Either.Success(tokenStored and userStored)
            }

            is Either.Error -> {
                Either.Error(response.error)
            }
        }
    }
}