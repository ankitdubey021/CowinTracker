package com.ankitdubey021.cowintracker.ui.components

import android.widget.CalendarView
import androidx.appcompat.view.ContextThemeWrapper
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.ankitdubey021.cowintracker.R
import com.ankitdubey021.cowintracker.ui.theme.Magenta
import com.ankitdubey021.cowintracker.utils.toDate
import com.ankitdubey021.cowintracker.utils.toDateStr
import java.util.*

@Composable
fun MDatePicker(dateSelectCallback : (Date) -> Unit) {

    val calendarShowState = remember { mutableStateOf(false) }
    val selectedDate = remember{ mutableStateOf( Date() ) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.06f),
                shape = MaterialTheme.shapes.small
            )
            .clickable {
                calendarShowState.value = true
            }


    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 18.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = selectedDate.value.toDateStr("dd MMM, yyyy"),
                style = MaterialTheme.typography.subtitle1.copy(color = Color.Black),
            )
            Icon(Icons.Filled.CalendarToday,"calendar", tint = Color.DarkGray)
        }

        if (calendarShowState.value)
            MDatePickerDialog(
                onDateSelected = {
                    calendarShowState.value = false
                    selectedDate.value = it
                    dateSelectCallback(it)
                },
                onDismissRequest = {
                    calendarShowState.value = false
                }
            )
    }
}

@Composable
fun MDatePickerDialog(onDateSelected: (Date) -> Unit, onDismissRequest: () -> Unit) {
    val selDate = remember { mutableStateOf(Date()) }

    //todo - add strings to resource after POC
    Dialog(onDismissRequest = { onDismissRequest() }, properties = DialogProperties()) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .background(
                    color = MaterialTheme.colors.surface,
                    shape = RoundedCornerShape(size = 16.dp)
                )
        ) {
            Column(
                Modifier
                    .defaultMinSize(minHeight = 72.dp)
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colors.primary,
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = "Select date".toUpperCase(Locale.ENGLISH),
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onPrimary
                )

                Spacer(modifier = Modifier.size(24.dp))

                Text(
                    text = selDate.value.toDateStr("MMM d, YYYY"),
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.onPrimary
                )

                Spacer(modifier = Modifier.size(16.dp))
            }

            CustomCalendarView(onDateSelected = {
                selDate.value = it
            })

            Spacer(modifier = Modifier.size(8.dp))

            Row(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(bottom = 16.dp, end = 16.dp)
            ) {
                TextButton(
                    onClick = onDismissRequest
                ) {
                    Text(
                        text = "Cancel",
                        style = MaterialTheme.typography.caption,
                        color = Color.Gray
                    )
                }

                TextButton(
                    onClick = {
                        onDateSelected(selDate.value)
                        onDismissRequest()
                    }
                ) {
                    Text(
                        text = "OK",
                        style = MaterialTheme.typography.caption,
                        color = Magenta
                    )
                }

            }
        }
    }
}


@Composable
fun CustomCalendarView(onDateSelected: (Date) -> Unit) {
    // Adds view to Compose
    AndroidView(
        modifier = Modifier.wrapContentSize(),
        factory = { context ->
            CalendarView(ContextThemeWrapper(context, R.style.CalenderViewCustom))
        },
        update = { view ->
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.MONTH, 1)

            view.minDate = System.currentTimeMillis()
            view.maxDate = calendar.timeInMillis

            view.setOnDateChangeListener { _, year, month, dayOfMonth ->
                onDateSelected(
                    "$year/${month + 1}/$dayOfMonth".toDate("yyyy/MM/dd")
                )
            }
        }
    )
}
