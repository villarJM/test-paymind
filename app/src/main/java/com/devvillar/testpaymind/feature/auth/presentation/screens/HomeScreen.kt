package com.devvillar.testpaymind.feature.auth.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.devvillar.testpaymind.R
import com.devvillar.testpaymind.core.states.UIState
import com.devvillar.testpaymind.feature.auth.presentation.viewmodels.HomeViewModel
import com.devvillar.testpaymind.core.ui.components.LoadingScreen

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToTransaction: () -> Unit = { },
    onLogout: () -> Unit = { }
) {

    val homeState by viewModel.homeUIState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    var username by remember { mutableStateOf("") }

    var textWidth by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(homeState) {
        when (homeState) {
            is UIState.Success -> {
                username = (homeState as UIState.Success).data.username
            }
            is UIState.Error -> {
                snackBarHostState.showSnackbar((homeState as UIState.Error).message)
            }
            is UIState.Initial -> {
                viewModel.getUser()
            }
            else -> { }
        }
    }

    if (homeState is UIState.Loading) {
        LoadingScreen()
        return
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
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
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.home_welcome_message, username),
                        modifier = Modifier
                            .weight(1f, fill = false),
                        maxLines = 3,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0XFF3441B5),
                                    Color(0XFFEE9A66)
                                ),
                                start = Offset(0f, 0f),
                                end = Offset(textWidth, 0f)
                            )
                        ),
                        onTextLayout = { result ->
                            textWidth = result.size.width.toFloat()
                        }
                    )
                    FilledTonalIconButton(
                        onClick = { onLogout() },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Logout Icon"
                        )
                    }
                }
                Spacer(Modifier.height(60.dp))
                Button(
                    onClick = { onNavigateToTransaction() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = 56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0XFF3441B5)
                    )

                ) {
                    Text(stringResource(R.string.home_go_to_transaction_button))
                }
            }
        }
    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen()
}