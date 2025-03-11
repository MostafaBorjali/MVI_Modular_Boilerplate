package com.mvi.modular.network.annotation

import okhttp3.Request
import retrofit2.Invocation

/**
 * Check this api call has withoutAuthentication annotation or not
 *
 * @return true if target request has annotation, false otherwise
 */
fun Request.withoutAuthentication(): Boolean {
    return this.getAnnotation(WithoutAuthentication::class.java) != null
}

private fun <T : Annotation> Request.getAnnotation(annotationClass: Class<T>): T? =
    this.tag(Invocation::class.java)?.method()?.getAnnotation(annotationClass)


