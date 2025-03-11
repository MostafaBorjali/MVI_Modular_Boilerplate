package com.mvi.modular.barcode.service

import android.graphics.Bitmap


interface BarcodeService {

    /**
     * Create QRCode from input data with application default width and height
     *
     * @param data Target information which needs to encode
     * @return QR Code
     */
    fun encode(data: String): Bitmap

    /**
     * Create QRCode from input data with input width and height
     *
     * @param data Target information which needs to encode
     * @param width QrCode width
     * @param height QrCode height
     * @return QR Code
     */
    fun encode(data: String, width: Int, height: Int): Bitmap
}