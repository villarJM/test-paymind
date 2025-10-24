package com.devvillar.testpaymind.feature.transaction.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devvillar.testpaymind.feature.transaction.presentation.viewmodels.TransactionViewModel
import kotlinx.coroutines.launch
import com.devvillar.testpaymind.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheetFilter(
    viewModel: TransactionViewModel,
    onDismissRequest: () -> Unit,
) {

    val scope = rememberCoroutineScope()

    val sheetState = rememberModalBottomSheetState()

    val startOfMonth = Calendar.getInstance().apply {
        set(Calendar.DAY_OF_MONTH, 1)
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    val endOfPeriod = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 23)
        set(Calendar.MINUTE, 59)
        set(Calendar.SECOND, 59)
        set(Calendar.MILLISECOND, 999)
    }

    var tempSortField by remember { mutableStateOf("date") }
    var tempSortDirection by remember { mutableStateOf("DESC") }
    var tempStartDateMillis by remember { mutableStateOf<Long?>(startOfMonth.timeInMillis) }
    var tempEndDateMillis by remember { mutableStateOf<Long?>(endOfPeriod.timeInMillis) }
    var expandedSortField by remember { mutableStateOf(false) }
    var expandedSortDirection by remember { mutableStateOf(false) }

    var showStartDatePicker by remember { mutableStateOf(false) }
    var showEndDatePicker by remember { mutableStateOf(false) }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.transaction_bottom_sheet_title),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            Box {
                TextField(
                    value = when (tempSortField) {
                        "date" -> stringResource(R.string.transaction_filter_by_date)
                        "amount" -> stringResource( R.string.transaction_filter_by_amount )
                        else -> tempSortField
                    },
                    onValueChange = { },
                    label = { Text( stringResource(R.string.transaction_label_order_by) ) },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    singleLine = true,
                    trailingIcon = {
                        IconButton(
                            onClick = { expandedSortField = !expandedSortField }
                        ) {
                            Icon(
                                imageVector = if (expandedSortField)
                                    Icons.Filled.KeyboardArrowUp
                                else
                                    Icons.Filled.KeyboardArrowDown,
                                contentDescription = "Expand"
                            )
                        }
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = Color(0XFFE1E3F5)
                    )
                )
                DropdownMenu(
                    expanded = expandedSortField,
                    onDismissRequest = { expandedSortField = false }
                ) {
                    listOf(
                        "date" to stringResource(R.string.transaction_filter_by_date),
                        "amount" to stringResource( R.string.transaction_filter_by_amount )
                    ).forEach { (value, label) ->
                        DropdownMenuItem(
                            text = { Text(label) },
                            onClick = {
                                tempSortField = value
                                expandedSortField = false
                            }
                        )
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            Box {
                TextField(
                    value = when (tempSortDirection) {
                        "ASC" -> stringResource( R.string.transaction_filter_by_asc )
                        "DESC" -> stringResource( R.string.transaction_filter_by_desc )
                        else -> tempSortDirection
                    },
                    onValueChange = { },
                    label = { Text("Direction") },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    singleLine = true,
                    trailingIcon = {
                        IconButton(
                            onClick = { expandedSortDirection = !expandedSortDirection }
                        ) {
                            Icon(
                                imageVector = if (expandedSortDirection)
                                    Icons.Filled.KeyboardArrowUp
                                else
                                    Icons.Filled.KeyboardArrowDown,
                                contentDescription = "Expand"
                            )
                        }
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = Color(0XFFE1E3F5)
                    )
                )
                DropdownMenu(
                    expanded = expandedSortDirection,
                    onDismissRequest = { expandedSortDirection = false }
                ) {
                    listOf(
                        "ASC" to stringResource(R.string.transaction_filter_by_asc),
                        "DESC" to stringResource(R.string.transaction_filter_by_desc)
                    ).forEach { (value, label) ->
                        DropdownMenuItem(
                            text = { Text(label) },
                            onClick = {
                                tempSortDirection = value
                                expandedSortDirection = false
                            }
                        )
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            TextField(
                value = tempStartDateMillis?.let {
                    SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(java.util.Date(it))
                } ?: "",
                onValueChange = { },
                label = { Text( stringResource(R.string.transaction_label_start_date) ) },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Select a date") },
                singleLine = true,
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { showStartDatePicker = true }) {
                        Icon(
                            imageVector = Icons.Filled.DateRange,
                            contentDescription = "Select date"
                        )
                    }
                },
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Color(0XFFE1E3F5)
                )
            )

            Spacer(Modifier.height(12.dp))

            TextField(
                value = tempEndDateMillis?.let {
                    SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(java.util.Date(it))
                } ?: "",
                onValueChange = { },
                label = { Text(stringResource(R.string.transaction_label_end_date)) },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Select a date") },
                singleLine = true,
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { showEndDatePicker = true }) {
                        Icon(
                            imageVector = Icons.Filled.DateRange,
                            contentDescription = "Select date"
                        )
                    }
                },
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Color(0XFFE1E3F5)
                )
            )

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = {
                    scope.launch {
                        val sortValue = "$tempSortField,$tempSortDirection"

                        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        val startDateStr = tempStartDateMillis?.let { dateFormat.format(java.util.Date(it)) } ?: ""
                        val endDateStr = tempEndDateMillis?.let { dateFormat.format(java.util.Date(it)) } ?: ""

                        viewModel.updateSort(sortValue)
                        viewModel.updateStartDate(startDateStr)
                        viewModel.updateEndDate(endDateStr)
                        viewModel.updatePage(0)
                        viewModel.getTransactions()

                        sheetState.hide()
                        onDismissRequest()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0XFF3441B5)
                )
            ) {
                Text(stringResource(R.string.transaction_apply_filters_button), fontSize = 16.sp)
            }

            Spacer(Modifier.height(40.dp))
        }
    }

    if (showStartDatePicker) {
        val initialUtc = tempStartDateMillis?.let { localMidnightToUtcMillis(it) }
        val datePickerState = rememberDatePickerState(initialSelectedDateMillis = initialUtc)
        DatePickerDialog(
            onDismissRequest = { showStartDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        val utc = datePickerState.selectedDateMillis
                        if (utc != null) {
                            tempStartDateMillis = utcMillisToLocalMidnight(utc)
                        }
                        showStartDatePicker = false
                    }
                ) {
                    Text("Accept")
                }
            },
            dismissButton = {
                TextButton(onClick = { showStartDatePicker = false }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    if (showEndDatePicker) {
        val initialUtc = tempEndDateMillis?.let { localMidnightToUtcMillis(it) }
        val datePickerState = rememberDatePickerState(initialSelectedDateMillis = initialUtc)
        DatePickerDialog(
            onDismissRequest = { showEndDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        val utc = datePickerState.selectedDateMillis
                        if (utc != null) {
                            tempEndDateMillis = utcMillisToLocalMidnight(utc)
                        }
                        showEndDatePicker = false
                    }
                ) {
                    Text("Accept")
                }
            },
            dismissButton = {
                TextButton(onClick = { showEndDatePicker = false }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

private fun utcMillisToLocalMidnight(utcMillis: Long): Long {
    val utcCal = Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply { timeInMillis = utcMillis }
    val y = utcCal.get(Calendar.YEAR)
    val m = utcCal.get(Calendar.MONTH)
    val d = utcCal.get(Calendar.DAY_OF_MONTH)
    val localCal = Calendar.getInstance().apply {
        set(Calendar.YEAR, y)
        set(Calendar.MONTH, m)
        set(Calendar.DAY_OF_MONTH, d)
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }
    return localCal.timeInMillis
}

private fun localMidnightToUtcMillis(localMillis: Long): Long {
    val localCal = Calendar.getInstance().apply { timeInMillis = localMillis }
    val y = localCal.get(Calendar.YEAR)
    val m = localCal.get(Calendar.MONTH)
    val d = localCal.get(Calendar.DAY_OF_MONTH)
    val utcCal = Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
        set(Calendar.YEAR, y)
        set(Calendar.MONTH, m)
        set(Calendar.DAY_OF_MONTH, d)
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }
    return utcCal.timeInMillis
}
