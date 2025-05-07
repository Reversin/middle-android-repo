package com.example.androidpracticumcustomview

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity

class XmlActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startXmlPracticum()
    }

    private fun startXmlPracticum() {
        val customContainer = CustomContainer(this)
        setContentView(customContainer)
        customContainer.setOnClickListener {
            finish()
        }

        val firstView = TextView(this).apply {
            text = "Первый элемент"
            textSize = 24f
        }

        val secondView = TextView(this).apply {
            text = "Второй элемент"
            textSize = 24f
        }

        customContainer.addView(firstView)
        customContainer.addView(secondView)
    }
}
