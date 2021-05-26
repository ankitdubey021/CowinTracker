package com.ankitdubey021.cowintracker.ui.screens.appointment

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ankitdubey021.cowintracker.data.AppointmentEntity

@Composable
fun AppointmentRow(appointmentEntity: AppointmentEntity){
    BaseRow(appointmentEntity)
    AppointmentDivider()
}

@Composable
fun BaseRow(appointmentEntity: AppointmentEntity){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)

    ) {
        val addrs = mutableListOf(
            appointmentEntity.address,
            appointmentEntity.distName,
            appointmentEntity.stateName,
            appointmentEntity.pincode,
        ).joinToString(", ")

        Box(
            Modifier
                .weight(1F)
        ){
            Column {
                Text(text = appointmentEntity.name, style = MaterialTheme.typography.subtitle1)
                Text(text = addrs, style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.W400))
            }
        }
        Box(
            /*contentAlignment = Alignment.CenterEnd*/
        ){
            IconButton(onClick = {  }) {
                Icon(Icons.Filled.ChevronRight, "")
            }
        }
    }
}

@Composable
fun AppointmentDivider(modifier: Modifier = Modifier) {
    Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.06f), thickness = 1.dp, modifier = modifier.padding(8.dp))
}