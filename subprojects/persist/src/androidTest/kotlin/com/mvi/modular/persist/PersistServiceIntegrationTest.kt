package com.mvi.modular.persist

import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.mvi.modular.persist.di.persistModule
import com.mvi.modular.persist.service.PersistService
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject


@SmallTest
class PersistServiceIntegrationTest : KoinTest {

    @get:Rule(order = 0)
    val koinTestRule = KoinTestRule.create {
        androidContext(
            InstrumentationRegistry.getInstrumentation().targetContext
        )
        modules(persistModule(true))
    }


    private val persistService by inject<PersistService>()


    @After
    fun tearDown() {
        persistService.removeAll()
    }

    @Test
    fun test_persist_string_and_retrieve_it_in_default_preference() {
        val key = "test_key"
        val value = "this is a test value"

        val result = persistService.putString(key, value)
        assertThat(result).isTrue()

        val data = persistService.getString(key)
        assertThat(data).isEqualTo(value)
    }

    @Test
    fun test_persist_boolean_and_retrieve_it_in_default_preference() {
        val key = "test_key"
        val value = true

        val result = persistService.putBoolean(key, value)
        assertThat(result).isTrue()

        val data = persistService.getBoolean(key)
        assertThat(data).isEqualTo(value)
    }

    @Test
    fun test_persist_long_and_retrieve_it_in_default_preference() {
        val key = "test_key"
        val value = 123456789L

        val result = persistService.putLong(key, value)
        assertThat(result).isTrue()

        val data = persistService.getLong(key)
        assertThat(data).isEqualTo(value)
    }

    @Test
    fun test_persist_int_and_retrieve_it_in_default_preference() {
        val key = "test_key"
        val value = 123456

        val result = persistService.putInt(key, value)
        assertThat(result).isTrue()

        val data = persistService.getInt(key)
        assertThat(data).isEqualTo(value)
    }

    @Test
    fun test_specific_key_is_exist_must_return_true_if_key_exist() {
        val key = "test_key"
        val value = 123456

        persistService.putInt(key, value)
        assertThat(persistService.contains(key)).isTrue()
    }

    @Test
    fun test_remove_key_and_check_it_removed_in_default_preference() {
        val key = "test_key"
        val value = "Hello world!"

        persistService.putString(key, value)
        persistService.removeKey(key)

        val data = persistService.getString(key)

        assertThat(data).isNull()
    }

    @Test
    fun test_remove_all_key_and_check_they_removed_in_default_preference() {
        val key = "test_key"
        val key1 = "test_key1"
        val key2 = "test_key2"

        val value = "Hello world!"
        val value1 = "Hello world! 1"
        val value2 = "Hello world! 2"

        persistService.putString(key, value)
        persistService.putString(key1, value1)
        persistService.putString(key2, value2)

        persistService.removeAll()

        val data = persistService.getString(key)
        val data1 = persistService.getString(key1)
        val data2 = persistService.getString(key2)

        assertThat(data).isNull()
        assertThat(data1).isNull()
        assertThat(data2).isNull()
    }
}