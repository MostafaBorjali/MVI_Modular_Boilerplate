package com.mvi.modular.utils.date

import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.TimeZone
import java.util.concurrent.TimeUnit


class DateTimeExtensionsTest {

    private var defaultOffset: Int = 0

    @Before
    fun setUp() {
        val timezone = TimeZone.getDefault()
        defaultOffset = timezone.rawOffset
        timezone.rawOffset = 0
        TimeZone.setDefault(timezone)
    }

    @After
    fun tearDown() {
        val timeZone = TimeZone.getDefault()
        timeZone.rawOffset = defaultOffset
        TimeZone.setDefault(timeZone)
    }

    @Test
    fun `test convert date string from some pattern to other pattern success`() {
        val from = "yyyy-MM-dd'T'HH:mm:ss.SSS"
        val to = "HH:mm - MM/dd/yyyy"
        val date = "2024-03-16T06:48:16.580"

        val result = date.convert(from, to)
        assertThat(result).isEqualTo("06:48 - 03/16/2024")
    }

    @Test
    fun `test convert date to string with target pattern success`() {
        val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse("2024-03-16T06:48:16.580")
        val to = "HH:mm - MM/dd/yyyy"

        val result = date?.convert(to)
        assertThat(result).isEqualTo("06:48 - 03/16/2024")
    }

    @Test
    fun `test add 2 days to date success`() {
        val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
            .parse("2024-03-16T06:48:16.580")
            ?: throw IllegalStateException("Date can not parse")

        val result = date + 2
        assertThat(result.convert("MM/dd/yyyy")).isEqualTo("03/18/2024")
    }

    @Test
    fun `test day left`() {
        val now = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())
        val day = 60 * 24 * 60

        assertThat((now - day).daysLeft()).isEqualTo(0)
        assertThat((now + day).daysLeft()).isEqualTo(1)
        assertThat((now + (day * 7)).daysLeft()).isEqualTo(7)
    }
}