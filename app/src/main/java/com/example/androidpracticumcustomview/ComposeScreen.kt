package com.example.androidpracticumcustomview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp

class ComposeScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCustomViewGroup(
                topContent = {
                    Text("Верхний элемент", fontSize = 24.sp)
                },
                bottomContent = {
                    Text("Нижний элемент", fontSize = 24.sp)
                }
            )
        }
    }
}
