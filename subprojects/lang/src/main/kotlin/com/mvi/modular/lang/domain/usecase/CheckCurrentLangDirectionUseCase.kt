package com.mvi.modular.lang.domain.usecase

import com.mvi.modular.lang.domain.model.LangDirection
import com.mvi.modular.lang.domain.model.Ltr
import com.mvi.modular.lang.domain.model.Rtl


internal interface CheckCurrentLangDirectionUseCase {
    /**
     *
     */
    operator fun invoke(direction: LangDirection): Boolean
}


internal class DefaultCheckCurrentLangDirectionUseCase(
    private val getCurrentLangUseCase: GetCurrentLangUseCase
) : CheckCurrentLangDirectionUseCase {

    override fun invoke(direction: LangDirection): Boolean {
        val lang = getCurrentLangUseCase()
        return when (direction) {
            Rtl -> lang.direction == "rtl"
            Ltr -> lang.direction == "ltr"
        }
    }
}