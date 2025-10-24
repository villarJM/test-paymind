package com.devvillar.testpaymind.feature.transaction.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
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
import com.devvillar.testpaymind.feature.transaction.domain.models.Content
import com.devvillar.testpaymind.feature.transaction.presentation.components.ModalBottomSheetFilter
import com.devvillar.testpaymind.feature.transaction.presentation.components.PaginationBar
import com.devvillar.testpaymind.feature.transaction.presentation.components.TransactionCard
import com.devvillar.testpaymind.feature.transaction.presentation.states.TransactionUIState
import com.devvillar.testpaymind.feature.transaction.presentation.viewmodels.TransactionViewModel
import com.devvillar.testpaymind.core.ui.components.LoadingScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionScreen(
    viewModel: TransactionViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit = { }

) {
    val transactionState by viewModel.transactionUIState.collectAsStateWithLifecycle()
    val currentPage by viewModel.page.collectAsStateWithLifecycle()

    val snackBarHostState = remember { SnackbarHostState() }
    var showBottomSheet by remember { mutableStateOf(false) }
    var textWidth by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(Unit) {
        viewModel.getTransactions()
    }

    LaunchedEffect(transactionState) {
        if (transactionState is TransactionUIState.Error) {
            snackBarHostState.showSnackbar((transactionState as TransactionUIState.Error).message)
        }
    }

    if (transactionState is TransactionUIState.Loading) {
        LoadingScreen()
        return
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0XFFF6F7FF),
        snackbarHost = { SnackbarHost(snackBarHostState) }
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
                    .padding(top = 56.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
            ) {

                HeaderSection(
                    textWidth = textWidth,
                    onTextLayout = { textWidth = it },
                    onFilterClick = { showBottomSheet = true },
                    onNavigateBack = onNavigateBack
                )

                Spacer(Modifier.height(20.dp))

                when (val state = transactionState) {
                    is TransactionUIState.Success -> {
                        TransactionListContent(
                            transactions = state.data.content,
                            totalPages = state.data.totalPages.toInt(),
                            currentPage = currentPage,
                            onPreviousPage = {
                                viewModel.updatePage(currentPage - 1)
                                viewModel.getTransactions()
                            },
                            onNextPage = {
                                viewModel.updatePage(currentPage + 1)
                                viewModel.getTransactions()
                            }
                        )
                    }
                    else -> { }
                }
            }
        }
    }

    if (showBottomSheet) {
        ModalBottomSheetFilter(
            viewModel = viewModel,
            onDismissRequest = { showBottomSheet = false }
        )
    }
}

@Composable
private fun HeaderSection(
    textWidth: Float,
    onTextLayout: (Float) -> Unit,
    onFilterClick: () -> Unit,
    onNavigateBack: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onNavigateBack
        ) {
            Icon(
                imageVector =  Icons.Filled.ChevronLeft,
                contentDescription = "Back"
            )
        }

        Text(
            text = stringResource(R.string.transaction_title),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f, fill = false),
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
                onTextLayout(result.size.width.toFloat())
            }
        )

        OutlinedIconButton(onClick = onFilterClick) {
            Icon(
                imageVector = Icons.Filled.FilterList,
                contentDescription = "Filters"
            )
        }
    }
}

@Composable
private fun TransactionListContent(
    transactions: List<Content>,
    totalPages: Int,
    currentPage: Int,
    onPreviousPage: () -> Unit,
    onNextPage: () -> Unit
) {
    if (transactions.isEmpty()) {
        EmptyState()
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier.padding(bottom = 72.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(transactions) { transaction ->
                    TransactionCard(transaction = transaction)
                }
            }

            PaginationBar(
                currentPage = currentPage,
                totalPages = totalPages,
                onPreviousPage = onPreviousPage,
                onNextPage = onNextPage,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
private fun EmptyState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No transactions found",
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionScreenPreview() {
    TransactionScreen()
}

