package com.mvi.modular.barcode.service

import android.graphics.Bitmap
import android.graphics.Color
import com.mvi.modular.barcode.core.BarcodeConstants.DEFAULT_HEIGHT
import com.mvi.modular.barcode.core.BarcodeConstants.DEFAULT_WIDTH
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import java.util.EnumMap


internal class DefaultBarcodeService : BarcodeService {


    override fun encode(data: String) = encode(data, DEFAULT_WIDTH, DEFAULT_HEIGHT)


    override fun encode(data: String, width: Int, height: Int): Bitmap {
        val matrix = matrix(data, width, height)
        val mWidth = matrix.width
        val mHeight = matrix.height

        val pixels = IntArray(width * height)
        for (y in 0 until height) {
            val offset = y * width
            for (x in 0 until width) {
                pixels[offset + x] = if (matrix[x, y]) Color.BLACK else Color.WHITE
            }
        }

        val bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
        return bitmap
    }

    private fun matrix(data: String, width: Int, height: Int): BitMatrix {
        val hints = EnumMap<EncodeHintType, Any>(EncodeHintType::class.java)
        hints[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.H
        return MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, width, height, hints)
    }
}