package com.ammotel.android.integration.domain.usecase

import com.ammotel.android.integration.domain.extension.LifecycleExtension
import com.ammotel.android.integration.domain.model.LifecycleStatus
import com.ammotel.android.integration.domain.repository.LifecycleExtensionRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class NotifyApplicationLifecycleChangedUseCaseTest {

    private lateinit var lifecycleExtensionRepository: LifecycleExtensionRepository
    private lateinit var notifyApplicationLifecycleChangedUseCase: NotifyApplicationLifecycleChangedUseCase

    @Before
    fun setUp() {
        lifecycleExtensionRepository = mock(LifecycleExtensionRepository::class.java)
        notifyApplicationLifecycleChangedUseCase = DefaultNotifyApplicationLifecycleChangedUseCase(
            lifecycleExtensionRepository
        )
    }


    @Test
    fun `test when notify application create, all extensions must notify`() {
        val extension = mock(LifecycleExtension::class.java)
        `when`(lifecycleExtensionRepository.getAllLifecycleExtensions()).thenReturn(listOf(extension))

        notifyApplicationLifecycleChangedUseCase(LifecycleStatus.Create)

        verify(extension, times(1)).onCreate()
    }

    @Test
    fun `test when notify application foreground, all extensions must notify`() {
        val extension = mock(LifecycleExtension::class.java)
        `when`(lifecycleExtensionRepository.getAllLifecycleExtensions()).thenReturn(listOf(extension))

        notifyApplicationLifecycleChangedUseCase(LifecycleStatus.Foreground)

        verify(extension, times(1)).onForeground()
    }

    @Test
    fun `test when notify application background, all extensions must notify`() {
        val extension = mock(LifecycleExtension::class.java)
        `when`(lifecycleExtensionRepository.getAllLifecycleExtensions()).thenReturn(listOf(extension))

        notifyApplicationLifecycleChangedUseCase(LifecycleStatus.Background)

        verify(extension, times(1)).onBackground()
    }
}