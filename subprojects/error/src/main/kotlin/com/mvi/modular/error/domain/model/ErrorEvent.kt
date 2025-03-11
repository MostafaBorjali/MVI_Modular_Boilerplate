package com.mvi.modular.error.domain.model

import com.mvi.modular.base.functional.Event


/**
 * Error info wrapper as a Event
 */
class ErrorEvent(errorInfo: ErrorInfo) : Event<ErrorInfo>(errorInfo)