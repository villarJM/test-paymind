package com.devvillar.testpaymind.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoadingScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .height(80.dp)
                    .width(80.dp),
                strokeWidth = 10.dp

            )
            Text(
                text = "Loading...",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 50.dp),
                fontSize = 18.sp
            )
        }

    }
}

@Composable
@Preview
fun LoadingScreenPreview() {
    LoadingScreen()
}