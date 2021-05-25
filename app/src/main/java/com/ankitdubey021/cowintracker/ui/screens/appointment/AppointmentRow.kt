package com.ankitdubey021.cowintracker.ui.screens.appointment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ankitdubey021.cowintracker.data.AppointmentEntity

@Composable
fun AppointmentRow(appointmentEntity: AppointmentEntity){
    BaseRow(appointmentEntity)
    AppointmentDivider()
}

@Composable
fun BaseRow(appointmentEntity: AppointmentEntity){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)

    ) {
        Text(text = appointmentEntity.address)
    }
}

@Composable
fun AppointmentDivider(modifier: Modifier = Modifier) {
    Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.06f), thickness = 1.dp, modifier = modifier.padding(8.dp))
}