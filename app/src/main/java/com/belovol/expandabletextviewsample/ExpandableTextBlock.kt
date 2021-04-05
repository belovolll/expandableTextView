package com.belovol.expandabletextviewsample

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible

class ExpandableTextBlock @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    val expandableTextView = ExpandableTextView(context, attrs, defStyleAttr)
    val hintTextView = TextView(context, attrs, defStyleAttr)

    init {
        orientation = VERTICAL
        addView(
                expandableTextView,
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        )
        addView(
                hintTextView,
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        )

        expandableTextView.canBeExpandedCallback = {
            hintTextView.isVisible = it
        }

        hintTextView.setOnClickListener {
            expandableTextView.expanded = true
        }

        if (isInEditMode) {
            expandableTextView.maxLines = 10
            expandableTextView.ellipsize = TextUtils.TruncateAt.END
            expandableTextView.setText(R.string.dummy_community_text)
            hintTextView.setText(R.string.show_full)
            hintTextView.setTextColor(Color.BLUE)
        }
    }
}