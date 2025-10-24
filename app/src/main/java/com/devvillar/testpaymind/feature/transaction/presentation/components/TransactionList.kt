package com.devvillar.testpaymind.feature.transaction.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devvillar.testpaymind.feature.transaction.domain.models.Content

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionList(
    transactions: List<Content>,
    totalPages: Int,
    currentPage: Int,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onPreviousPage: () -> Unit,
    onNextPage: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pullToRefreshState = rememberPullToRefreshState()

    if (transactions.isEmpty()) {
        EmptyTransactionState()
    } else {
        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = onRefresh,
            state = pullToRefreshState,
            modifier = modifier.fillMaxSize()
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier.padding(bottom = 72.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(
                        items = transactions,
                        key = { it.id }
                    ) { transaction ->
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
}

@Composable
private fun EmptyTransactionState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No se encontraron transacciones",
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}

