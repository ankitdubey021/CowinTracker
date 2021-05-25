package com.ankitdubey021.cowintracker.ui.screens.appointment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ankitdubey021.cowintracker.ui.CowinToolbar
import com.ankitdubey021.cowintracker.ui.screens.home.HomeViewModel

@Composable
fun AppointmentScreen(navController: NavController, viewModel: HomeViewModel, distId : String?){
    viewModel.getAppointments(distId?.toInt() ?: -1)

    val appointments = viewModel.appointments.observeAsState()

    Scaffold(
        topBar = { CowinToolbar() }
    ){
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(12.dp)) {
                    appointments.value?.forEach { appointment ->
                        AppointmentRow(appointmentEntity = appointment)
                    }
                }
            }
        }
    }
}