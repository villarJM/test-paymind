package com.devvillar.testpaymind.feature.auth.presentation.screens

import android.R.attr.singleLine
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun LoginScreen() {

    var textWidth by remember { mutableFloatStateOf(0f) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 56.dp, start = 20.dp, end = 20.dp)

            ) {
                Text(
                    "Hello, \nWelcome Back",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0XFF3441B5),
                                Color(0XFFEE9A66),

                            ),
                            start = Offset(0f, 0f),
                            end = Offset(400f, 0f)
                        )
                    ),
                    onTextLayout = { result ->
                        textWidth = result.size.width.toFloat()
                    }
                )

                Spacer(Modifier.height(20.dp))
                Text("Hey, welcome back to your special place")
                Spacer(Modifier.height(30.dp))
                TextField(
                    state = rememberTextFieldState(""),
                    label = { Text("Email") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = Color(0XFFE1E3F5)
                    )
                )
                Spacer(Modifier.height(20.dp))
                TextField(
                    state = rememberTextFieldState(""),
                    label = { Text("Password") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = Color(0XFFE1E3F5)

                    )
                )
                Spacer(Modifier.height(30.dp))
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = 56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0XFF3441B5)
                    )

                ) {
                    Text("Sign In")
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}