package com.mvi.modular.utils.binary

import com.google.common.truth.Truth
import org.junit.Test


class ByteExtensionsTest {


    @Test
    fun `test encode string to hex success`() {
        val raw = "Hello world! this is Amortel app"
        val hex = "48656c6c6f20776f726c6421207468697320697320416d6f7274656c20617070"

        Truth.assertThat(raw.encodeHex()).isEqualTo(hex)
    }

    @Test
    fun `test encode string bytearray to hex success`() {
        val raw = "Hello world! this is Amortel app".toByteArray()
        val hex = "48656c6c6f20776f726c6421207468697320697320416d6f7274656c20617070"

        Truth.assertThat(raw.encodeHex()).isEqualTo(hex)
    }

    @Test
    fun `test decode hex string to raw string success`() {
        val hex = "48656c6c6f20776f726c6421207468697320697320416d6f7274656c20617070"
        val raw = "Hello world! this is Amortel app"

        val result = String(hex.decodeHex())
        Truth.assertThat(result).isEqualTo(raw)
    }
}