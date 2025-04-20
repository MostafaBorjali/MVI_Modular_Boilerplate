package com.mvi.modular.user.data.repository

import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.common.truth.Truth.assertThat
import com.mvi.modular.auth.domain.model.AuthToken
import com.mvi.modular.auth.domain.model.TokenType
import com.mvi.modular.auth.service.AuthService
import com.mvi.modular.base.functional.Either
import com.mvi.modular.error.domain.model.Error
import com.mvi.modular.error.service.ErrorService
import com.mvi.modular.persist.service.PersistService
import com.mvi.modular.user.core.UserConstants
import com.mvi.modular.user.data.datasource.remote.UserLoginRemoteDataSource
import com.mvi.modular.user.data.datasource.remote.dto.UserInfoDto
import com.mvi.modular.user.data.datasource.remote.dto.UserRegisterResponseDto
import com.mvi.modular.user.data.datasource.remote.dto.UserVerifyResponseDto
import com.mvi.modular.user.data.mapper.toJson
import com.mvi.modular.user.domain.model.UserRegisterInfo
import com.mvi.modular.user.domain.repository.UserLoginRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`


internal class UserLoginRepositoryTest {

    private lateinit var userLoginRepository: UserLoginRepository
    private lateinit var userLoginRemoteDataSource: UserLoginRemoteDataSource
    private lateinit var googleIdTokenCredential: GoogleIdTokenCredential
    private lateinit var authService: AuthService
    private lateinit var errorService: ErrorService
    private lateinit var persistService: PersistService

    @Before
    fun setUp() {
        userLoginRemoteDataSource = Mockito.mock(UserLoginRemoteDataSource::class.java)
        authService = Mockito.mock(AuthService::class.java)
        errorService = Mockito.mock(ErrorService::class.java)
        persistService = Mockito.mock(PersistService::class.java)
        googleIdTokenCredential = Mockito.mock(GoogleIdTokenCredential::class.java)

        userLoginRepository = DefaultUserLoginRepository(
            userLoginRemoteDataSource, authService, persistService
        )
    }


    @Test
    fun `test register success must return UserRegisterInfo`() = runBlocking {
        val email = "test@gmail.com"
        `when`(userLoginRemoteDataSource.register(email))
            .thenReturn(
                Either.Success(
                    UserRegisterResponseDto(
                        resendDuration = 10,
                        length = 5
                    )
                )
            )

        val result = userLoginRepository.register(email)
        assertThat(result).isInstanceOf(Either.Success::class.java)
        assertThat((result as Either.Success).data).isEqualTo(
            UserRegisterInfo(
                resendDuration = 10,
                verificationCodeLength = 5,
            )
        )
    }

    @Test
    fun `test register error must return correct error`() = runBlocking {
        val email = "test@gmail.com"
        `when`(userLoginRemoteDataSource.register(email))
            .thenReturn(
                Either.Error(Error.NetworkError.ConnectionError())
            )

        val result = userLoginRepository.register(email)
        assertThat(result).isInstanceOf(Either.Error::class.java)
        assertThat((result as Either.Error).error).isEqualTo(
            Error.NetworkError.ConnectionError()
        )
    }

    @Test
    fun `test verify success must return true if get result and persist result successfully`() =
        runBlocking {
            val email = "test@gmail.com"
            val code = "12345"
            val user = UserInfoDto(
                id = 1,
                status = 1,
                email = email,
                avatar = null,
                name = null,
                lang = "en"
            )
            `when`(userLoginRemoteDataSource.verify(email, code))
                .thenReturn(
                    Either.Success(
                        UserVerifyResponseDto(
                            token = "token",
                            expiredAt = 1,
                            user = user
                        )
                    )
                )

            val authToken = AuthToken(
                value = "token",
                expireAt = 1
            )
            `when`(authService.insert(authToken, TokenType.AccessToken)).thenReturn(true)

            `when`(persistService.putString(UserConstants.KEY_USER_INFO, user.toJson()))
                .thenReturn(true)

            val result = userLoginRepository.verify(email, code)
            assertThat(result).isInstanceOf(Either.Success::class.java)
            assertThat((result as Either.Success).data).isTrue()
        }


    @Test
    fun `test verify must return false if get result and persist token not successful`() =
        runBlocking {
            val email = "test@gmail.com"
            val code = "12345"
            val user = UserInfoDto(
                id = 1,
                status = 1,
                email = email,
                avatar = null,
                name = null,
                lang = "en"
            )
            `when`(userLoginRemoteDataSource.verify(email, code))
                .thenReturn(
                    Either.Success(
                        UserVerifyResponseDto(
                            token = "token",
                            expiredAt = 1,
                            user = user
                        )
                    )
                )

            val authToken = AuthToken(
                value = "token",
                expireAt = 1
            )
            `when`(authService.insert(authToken, TokenType.AccessToken)).thenReturn(false)

            `when`(persistService.putString(UserConstants.KEY_USER_INFO, user.toJson()))
                .thenReturn(true)

            val result = userLoginRepository.verify(email, code)
            assertThat(result).isInstanceOf(Either.Success::class.java)
            assertThat((result as Either.Success).data).isFalse()
        }

    @Test
    fun `test verify must return false if get result and persist user not successful`() =
        runBlocking {
            val email = "test@gmail.com"
            val code = "12345"
            val user = UserInfoDto(
                id = 1,
                status = 1,
                email = email,
                avatar = null,
                name = null,
                lang = "en"
            )
            `when`(userLoginRemoteDataSource.verify(email, code))
                .thenReturn(
                    Either.Success(
                        UserVerifyResponseDto(
                            token = "token",
                            expiredAt = 1,
                            user = user
                        )
                    )
                )

            val authToken = AuthToken(
                value = "token",
                expireAt = 1
            )
            `when`(authService.insert(authToken, TokenType.AccessToken)).thenReturn(true)

            `when`(persistService.putString(UserConstants.KEY_USER_INFO, user.toJson()))
                .thenReturn(false)

            val result = userLoginRepository.verify(email, code)
            assertThat(result).isInstanceOf(Either.Success::class.java)
            assertThat((result as Either.Success).data).isFalse()
        }


    @Test
    fun `test verify error must return correct error`() = runBlocking {
        val email = "test@gmail.com"
        val code = "12345"
        `when`(userLoginRemoteDataSource.verify(email, code))
            .thenReturn(
                Either.Error(Error.NetworkError.ConnectionError())
            )

        val result = userLoginRepository.verify(email, code)
        assertThat(result).isInstanceOf(Either.Error::class.java)
        assertThat((result as Either.Error).error).isEqualTo(
            Error.NetworkError.ConnectionError()
        )
    }

}