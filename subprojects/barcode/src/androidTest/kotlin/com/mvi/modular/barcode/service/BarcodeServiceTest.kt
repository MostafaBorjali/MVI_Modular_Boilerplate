package com.mvi.modular.barcode.service

import androidx.test.filters.SmallTest
import com.mvi.modular.barcode.di.barcodeModule
import com.google.common.truth.Truth.assertThat
import com.google.zxing.BinaryBitmap
import com.google.zxing.LuminanceSource
import com.google.zxing.RGBLuminanceSource
import com.google.zxing.common.HybridBinarizer
import com.google.zxing.qrcode.QRCodeReader
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject


@SmallTest
class BarcodeServiceTest : KoinTest {

    @get:Rule(order = 0)
    val koinTestRule = KoinTestRule.create {
        modules(barcodeModule)
    }

    private val barcodeService by inject<BarcodeService>()


    @Test
    fun test_encode_must_returns_bitmap_that_contains_true_data() {
        val data = "Hello"
        val qrcode = barcodeService.encode(data)

        val pixels = IntArray(qrcode.width * qrcode.height)
        qrcode.getPixels(pixels, 0, qrcode.width, 0, 0, qrcode.width, qrcode.height)
        val source: LuminanceSource = RGBLuminanceSource(qrcode.width, qrcode.height, pixels)

        val bitmap = BinaryBitmap(HybridBinarizer(source))

        val decodedData = QRCodeReader().decode(bitmap).text
        assertThat(decodedData).isEqualTo(data)
    }
}