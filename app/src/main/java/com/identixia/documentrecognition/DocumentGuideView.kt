package com.identixia.documentrecognition

import android.content.Context
import android.graphics.RectF
import android.util.AttributeSet
import com.identixia.documentreader.kit.DocumentGuideView as KitDocumentGuideView

/** Product-facing guide view for the recognition-only app (`applicationId` documentrecognition). */
class DocumentGuideView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : KitDocumentGuideView(context, attrs) {
    companion object {
        fun passportGuideRect(w: Float, h: Float): RectF = KitDocumentGuideView.passportGuideRect(w, h)
    }
}
