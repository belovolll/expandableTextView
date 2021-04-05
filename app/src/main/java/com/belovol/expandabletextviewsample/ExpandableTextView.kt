package com.belovol.expandabletextviewsample

import android.content.Context
import android.graphics.Canvas
import android.os.Build
import android.text.DynamicLayout
import android.text.Layout
import android.text.SpannableStringBuilder
import android.util.AttributeSet
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView

class ExpandableTextView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    var canBeExpandedCallback: ((canExpanded: Boolean) -> Unit)? = null

    var expanded = false
        set(value) {
            if (field != value) {
                field = value
                invalidate()
            }
        }

    private var previousMaxLinesValue = Integer.MAX_VALUE

    var realLinesCount = 0
        private set

    private val dynamicLayoutSpannable = SpannableStringBuilder()
    private var dynamicLayoutSpannableString = dynamicLayoutSpannable.toString()

    private lateinit var dynamicLayout: DynamicLayout

    override fun setMaxLines(maxLines: Int) {
        previousMaxLinesValue = maxLines
        super.setMaxLines(maxLines)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        if (!::dynamicLayout.isInitialized || dynamicLayout.width != width) {
            dynamicLayout = getTextViewDynamicLayout(dynamicLayoutSpannable, this)
        }

        if (dynamicLayoutSpannableString != text.toString()) {
            dynamicLayoutSpannable.clear()
            dynamicLayoutSpannable.append(text)
            dynamicLayoutSpannableString = dynamicLayoutSpannable.toString()
        }

        realLinesCount = dynamicLayout.lineCount

        canBeExpandedCallback?.invoke(!expanded && realLinesCount > maxLines)
    }

    override fun onDraw(canvas: Canvas) {
        if (expanded) {
            if (maxLines != Integer.MAX_VALUE) {
                maxLines = Integer.MAX_VALUE
            }
        } else {
            if (maxLines != previousMaxLinesValue) {
                maxLines = previousMaxLinesValue
            }
        }
        super.onDraw(canvas)
    }

    @Suppress("DEPRECATION")
    private fun getTextViewDynamicLayout(spannable: SpannableStringBuilder, tv: TextView): DynamicLayout {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            DynamicLayout.Builder.obtain(spannable, tv.paint, tv.width)
                    .build()
        } else
            DynamicLayout(
                    spannable,
                    spannable,
                    tv.paint,
                    width,
                    Layout.Alignment.ALIGN_NORMAL,
                    1F,
                    0F,
                    true
            )
    }

}