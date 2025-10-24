package com.devvillar.testpaymind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.devvillar.testpaymind.core.ui.navegation.TestPayMindNavigation
import com.devvillar.testpaymind.core.ui.theme.TestPayMindTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestPayMindTheme(
                darkTheme = false
            ) {
                TestPayMindNavigation()
            }
        }
    }
}