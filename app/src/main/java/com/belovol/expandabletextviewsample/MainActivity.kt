package com.belovol.expandabletextviewsample

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.updateMargins
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val item1 = findViewById<MaterialCardView>(R.id.item1)

        val expandableTextBlock1 = item1.findViewById<ExpandableTextBlock>(R.id.text)

        with(expandableTextBlock1) {
            hintTextView.setText(R.string.show_full)
            hintTextView.setTextColor(Color.BLUE)
            hintTextView.layoutParams = (hintTextView.layoutParams as ViewGroup.MarginLayoutParams).apply {
                updateMargins(top = 8.px)
            }
            expandableTextView.maxLines = 10
            expandableTextView.setText(R.string.dummy_community_text)
        }

        item1.findViewById<TextView>(R.id.comments).text = 1000.toString()
        item1.findViewById<TextView>(R.id.likes).text = 723.toString()
        item1.findViewById<TextView>(R.id.views).text = 1500.toString()

        val item2 = findViewById<MaterialCardView>(R.id.item2)

        val expandableTextBlock2 = item2.findViewById<ExpandableTextBlock>(R.id.text)

        with(expandableTextBlock2) {
            hintTextView.setText(R.string.show_full)
            hintTextView.setTextColor(Color.BLUE)
            hintTextView.layoutParams = (hintTextView.layoutParams as ViewGroup.MarginLayoutParams).apply {
                updateMargins(top = 8.px)
            }
            expandableTextView.maxLines = 10
            expandableTextView.setText(R.string.dummy_community_text2)
        }

        item2.findViewById<TextView>(R.id.comments).text = 100.toString()
        item2.findViewById<TextView>(R.id.likes).text = 72.toString()
        item2.findViewById<TextView>(R.id.views).text = 150.toString()

    }
}

val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()