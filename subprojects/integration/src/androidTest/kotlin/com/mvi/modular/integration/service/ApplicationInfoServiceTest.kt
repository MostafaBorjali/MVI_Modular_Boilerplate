package com.mvi.modular.integration.service

import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.mvi.modular.integration.di.integrationModule
import com.mvi.modular.integration.domain.model.ApplicationInfo
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

@SmallTest
class ApplicationInfoServiceTest : KoinTest {

    @get:Rule(order = 0)
    val koinTestRule = KoinTestRule.create {
        androidContext(InstrumentationRegistry.getInstrumentation().targetContext)
        modules(integrationModule)
    }

    private val applicationInfoService: ApplicationInfoService by inject()


    @Test
    fun test_initialize_and_get_application_info_must_return_correct_info() {
        val info = ApplicationInfo(
            applicationId = "net.amortel.android",
            versionCode = 1,
            versionName = "1.0",
            debug = true
        )

        applicationInfoService.initialize(info)

        val result = applicationInfoService.applicationInfo()
        assertThat(result).isNotNull()
        assertThat(result).isEqualTo(info)
    }

    @Test
    fun test_get_application_info_must_return_null_when_service_not_initialized() {
        val result = applicationInfoService.applicationInfo()
        assertThat(result).isNull()
    }
}