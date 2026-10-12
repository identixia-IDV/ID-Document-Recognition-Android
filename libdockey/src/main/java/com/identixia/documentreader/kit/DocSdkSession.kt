package com.identixia.documentreader.kit

import android.content.Context
import android.graphics.Bitmap
import com.identixia.documentrecognitionsdk.DocumentRecognitionSDK

/**
 * Session helper for still OCR / MRZ / barcode (recognition-only product).
 *
 * Camera guide crops must pass [alreadyCropped]=true so the engine skips re-detect.
 * Gallery full-frame picks leave it false.
 */
object DocSdkSession {
    fun startGallery() = DocumentRecognitionSDK.startNewSession(
        "{\"scenario\":\"FullProcess\",\"series\":false}"
    )

    /**
     * Recognition only (no authenticity).
     * @return Pair(json, optional user-facing deny message shown before/after the call)
     */
    fun recognize(
        context: Context,
        front: Bitmap,
        back: Bitmap? = null,
        alreadyCropped: Boolean = false,
    ): Pair<String, String?> {
        val status = LicenseStatus.current()
        val deny = status.denyMessage(wantRecognition = true, wantAuthenticity = false)
        return try {
            DocumentRecognitionSDK.documentRecognition(front, back, alreadyCropped) to deny
        } catch (e: Exception) {
            "{\"msg\":\"${e.message}\"}" to deny
        }
    }
}
