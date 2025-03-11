package com.mvi.modular.user.di

import com.mvi.modular.user.data.datasource.remote.DefaultUserLoginRemoteDataSource
import com.mvi.modular.user.data.datasource.remote.DefaultUserProfileRemoteDataSource
import com.mvi.modular.user.data.datasource.remote.UserLoginApi
import com.mvi.modular.user.data.datasource.remote.UserLoginRemoteDataSource
import com.mvi.modular.user.data.datasource.remote.UserProfileApi
import com.mvi.modular.user.data.datasource.remote.UserProfileRemoteDataSource
import com.mvi.modular.user.data.repository.DefaultUserLoginRepository
import com.mvi.modular.user.data.repository.DefaultUserProfileRepository
import com.mvi.modular.user.domain.repository.UserLoginRepository
import com.mvi.modular.user.domain.repository.UserProfileRepository
import com.mvi.modular.user.domain.usecase.DefaultDeleteUserProfileUseCase
import com.mvi.modular.user.domain.usecase.DefaultFetchUserInfoUseCase
import com.mvi.modular.user.domain.usecase.DefaultGetUseInfoUseCase
import com.mvi.modular.user.domain.usecase.DefaultLogoutUserUseCase
import com.mvi.modular.user.domain.usecase.DefaultUpdateUserProfileUseCase
import com.mvi.modular.user.domain.usecase.DefaultUserGoogleSignInUseCase
import com.mvi.modular.user.domain.usecase.DefaultUserRegisterUseCase
import com.mvi.modular.user.domain.usecase.DefaultUserVerificationUseCase
import com.mvi.modular.user.service.DefaultUserLoginService
import com.mvi.modular.user.service.DefaultUserProfileService
import com.mvi.modular.user.service.UserLoginService
import com.mvi.modular.user.service.UserProfileService
import org.koin.dsl.module
import retrofit2.Retrofit


val userModule = module {

    single<UserLoginRemoteDataSource> {
        fun provideLoginApi(retrofit: Retrofit): UserLoginApi {
            return retrofit.create(UserLoginApi::class.java)
        }

        DefaultUserLoginRemoteDataSource(
            userLoginApi = provideLoginApi(get()),
            errorService = get(),
        )
    }

    single<UserProfileRemoteDataSource> {
        fun provideProfileApi(retrofit: Retrofit): UserProfileApi {
            return retrofit.create(UserProfileApi::class.java)
        }

        DefaultUserProfileRemoteDataSource(
            userProfileApi = provideProfileApi(get()),
            errorService = get(),
        )
    }

    single<UserLoginRepository> {
        DefaultUserLoginRepository(
            userLoginRemoteDataSource = get(),
            authService = get(),
            persistService = get(),
        )
    }

    single<UserProfileRepository> {
        DefaultUserProfileRepository(
            userProfileRemoteDataSource = get(),
            persistService = get(),
            authService = get(),
        )
    }

    single<UserLoginService> {
        val userRegisterUseCase = DefaultUserRegisterUseCase(
            userLoginRepository = get(),
        )
        val userVerificationUseCase = DefaultUserVerificationUseCase(
            userLoginRepository = get(),
        )
        val userGoogleSignInUseCase = DefaultUserGoogleSignInUseCase(
            userLoginRepository = get(),
        )
        DefaultUserLoginService(
            userRegisterUseCase = userRegisterUseCase,
            userVerificationUseCase = userVerificationUseCase,
            userGoogleSignInUseCase = userGoogleSignInUseCase
        )
    }

    single<UserProfileService> {
        val fetchUserInfoUseCase = DefaultFetchUserInfoUseCase(
            userProfileRepository = get(),
        )
        val getUserInfoUseCase = DefaultGetUseInfoUseCase(
            userProfileRepository = get(),
        )
        val logoutUserUseCase = DefaultLogoutUserUseCase(
            userProfileRepository = get(),
        )
        val updateUserProfileUseCase = DefaultUpdateUserProfileUseCase(
            userProfileRepository = get(),
        )
        val deleteUserProfileUseCase = DefaultDeleteUserProfileUseCase(
            userProfileRepository = get(),
        )
        DefaultUserProfileService(
            fetchUserInfoUseCase = fetchUserInfoUseCase,
            getUserInfoUseCase = getUserInfoUseCase,
            logoutUserUseCase = logoutUserUseCase,
            updateUserProfileUseCase = updateUserProfileUseCase,
            deleteUserProfileUseCase = deleteUserProfileUseCase,
        )
    }
}