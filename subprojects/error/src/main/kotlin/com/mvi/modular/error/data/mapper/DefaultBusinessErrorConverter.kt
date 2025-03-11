package com.mvi.modular.error.data.mapper

import com.mvi.modular.error.core.ErrorConstants
import com.mvi.modular.error.domain.mapper.BusinessErrorConverter
import com.mvi.modular.error.domain.model.BusinessError
import org.json.JSONObject


internal class DefaultBusinessErrorConverter : BusinessErrorConverter {


    override fun convert(errorBody: String): BusinessError? {
        return try {
            val jsonObject = JSONObject(errorBody)
            BusinessError(
                internalCode = jsonObject.getInt(ErrorConstants.KEY_STATUS_CODE),
                message = jsonObject.getString(ErrorConstants.KEY_ERROR_MESSAGE),
//                detail = jsonObject.getString(ErrorConstants.KEY_ERROR_DETAIL)
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }
}