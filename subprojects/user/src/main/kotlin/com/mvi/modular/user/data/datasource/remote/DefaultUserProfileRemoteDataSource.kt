package com.mvi.modular.user.data.datasource.remote

import com.mvi.modular.base.functional.Either
import com.mvi.modular.error.domain.model.Error
import com.mvi.modular.error.extension.launchApiCatchingError
import com.mvi.modular.error.service.ErrorService
import com.mvi.modular.user.data.datasource.remote.dto.UpdateUserProfileRequestDto
import com.mvi.modular.user.data.datasource.remote.dto.UserInfoDto


internal class DefaultUserProfileRemoteDataSource(
    private val userProfileApi: UserProfileApi,
    private val errorService: ErrorService,
) : UserProfileRemoteDataSource {


    override suspend fun profile(): Either<UserInfoDto, Error> {
        return errorService.launchApiCatchingError {
            userProfileApi.profile().response
        }
    }

    override suspend fun update(token: String?, lang: String?): Either<UserInfoDto, Error> {
        return errorService.launchApiCatchingError {
            userProfileApi.update(UpdateUserProfileRequestDto(token, lang)).response
        }
    }

    override suspend fun delete(): Either<Unit, Error> {
        return errorService.launchApiCatchingError {
            userProfileApi.delete()
        }
    }
}