package com.mvi.modular.persist.data.repository

import android.content.SharedPreferences
import com.google.common.truth.Truth.assertThat
import com.mvi.modular.persist.domain.repository.PersistRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.anyBoolean
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.anyLong
import org.mockito.Mockito.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class PersistRepositoryTest {

    private lateinit var persistRepository: PersistRepository
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var defaultEditor: SharedPreferences.Editor

    @Before
    fun setUp() {
        sharedPreferences = mock(SharedPreferences::class.java)

        defaultEditor = mock(SharedPreferences.Editor::class.java)

        `when`(sharedPreferences.edit()).thenReturn(defaultEditor)
        `when`(defaultEditor.putString(anyString(), anyString())).thenReturn(defaultEditor)
        `when`(defaultEditor.putBoolean(anyString(), anyBoolean())).thenReturn(defaultEditor)
        `when`(defaultEditor.putLong(anyString(), anyLong())).thenReturn(defaultEditor)
        `when`(defaultEditor.putInt(anyString(), anyInt())).thenReturn(defaultEditor)
        `when`(defaultEditor.remove(anyString())).thenReturn(defaultEditor)
        `when`(defaultEditor.clear()).thenReturn(defaultEditor)
        `when`(sharedPreferences.contains(anyString())).thenReturn(true)

        persistRepository = DefaultPersistRepository(
            sharedPreferences
        )
    }


    @Test
    fun `test store raw string must call on default preference`() {
        val key = "test_key"
        val value = "test_value"

        persistRepository.storeString(key, value)
        verify(defaultEditor, times(1)).putString(key, value)
    }

    @Test
    fun `test store raw boolean must call on default preference`() {
        val key = "test_key"
        val value = false

        persistRepository.storeBoolean(key, value)
        verify(defaultEditor, times(1)).putBoolean(key, value)
    }

    @Test
    fun `test store raw long must call on default preference`() {
        val key = "test_key"
        val value = 20L

        persistRepository.storeLong(key, value)
        verify(defaultEditor, times(1)).putLong(key, value)
    }

    @Test
    fun `test store raw int must call on default preference`() {
        val key = "test_key"
        val value = 20

        persistRepository.storeInt(key, value)
        verify(defaultEditor, times(1)).putInt(key, value)
    }

    @Test
    fun `test retrieve raw string must call on default preference`() {
        val key = "test_key"

        persistRepository.retrieveString(key)

        verify(sharedPreferences, times(1)).getString(key, null)

        `when`(sharedPreferences.contains(anyString())).thenReturn(false)

        val result = persistRepository.retrieveString(key)
        assertThat(result).isNull()
    }

    @Test
    fun `test retrieve raw boolean must call on default preference`() {
        val key = "test_key"

        persistRepository.retrieveBoolean(key)

        verify(sharedPreferences, times(1)).getBoolean(key, false)

        `when`(sharedPreferences.contains(anyString())).thenReturn(false)

        val result = persistRepository.retrieveBoolean(key)
        assertThat(result).isNull()
    }

    @Test
    fun `test retrieve raw long must call on default preference`() {
        val key = "test_key"

        persistRepository.retrieveLong(key)

        verify(sharedPreferences, times(1)).getLong(key, 0L)

        `when`(sharedPreferences.contains(anyString())).thenReturn(false)

        val result = persistRepository.retrieveLong(key)
        assertThat(result).isNull()
    }

    @Test
    fun `test retrieve raw int must call on default preference`() {
        val key = "test_key"

        persistRepository.retrieveInt(key)

        verify(sharedPreferences, times(1)).getInt(key, 0)

        `when`(sharedPreferences.contains(anyString())).thenReturn(false)

        val result = persistRepository.retrieveInt(key)
        assertThat(result).isNull()
    }

    @Test
    fun `test remove raw specific key must call remove on default preference`() {
        val key = "test_key"

        persistRepository.removeSpecificKey(key)
        verify(defaultEditor, times(1)).remove(key)
    }

    @Test
    fun `test remove all raw key must call clear on default preference`() {
        persistRepository.removeAllKey()
        verify(defaultEditor, times(1)).clear()
    }

}