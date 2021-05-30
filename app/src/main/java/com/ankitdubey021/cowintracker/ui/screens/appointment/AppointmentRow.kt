package com.ankitdubey021.cowintracker.ui.screens.appointment

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ankitdubey021.cowintracker.data.HospitalEntity

@Composable
fun AppointmentRow(hospital: HospitalEntity){
    Column {
        BaseRow(hospital)
        AppointmentDivider()
    }
}

@Composable
fun BaseRow(entity: HospitalEntity){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 12.dp, bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val addrs = mutableListOf(
            entity.address,
            entity.distName,
            entity.stateName,
            entity.pincode,
        ).joinToString(", ")

        Box(
            Modifier
                .weight(1F)
        ){
            Column {
                Text(text = entity.name, style = MaterialTheme.typography.subtitle1)
                Text(text = addrs, style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.W400))
            }
        }
        Icon(Icons.Filled.ChevronRight, "")
    }
}

@Composable
fun AppointmentDivider(modifier: Modifier = Modifier) {
    Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.06f), thickness = 1.dp, modifier = modifier.padding(start = 8.dp, end = 8.dp))
}