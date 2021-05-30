package com.ankitdubey021.cowintracker.ui.screens.appointment

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ankitdubey021.cowintracker.R
import com.ankitdubey021.cowintracker.ui.components.CowinToolbar
import com.ankitdubey021.cowintracker.data.HospitalEntity
import com.ankitdubey021.cowintracker.ui.screens.home.HomeViewModel
import com.ankitdubey021.cowintracker.utils.toDateStr
import java.util.*

@Composable
fun AppointmentScreen(
    navController: NavController,
    viewModel: HomeViewModel,
    distId: String?,
    date: String?
) {
    viewModel.getAppointments(distId?.toInt() ?: -1, date ?: Date().toDateStr("dd-MM-yyyy"))

    val appointments = viewModel.appointments.observeAsState()
    val hospitalList = appointments.value?.sortedBy { it.name }

    Scaffold(
        topBar = {
            CowinToolbar(title = "Hospital List", onBackClicked = {
                navController.navigateUp()
            })
        }
    ) {
        if (appointments.value != null && appointments.value!!.isEmpty()) {
            ShowEmptyState()
        }
        else
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Card(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.padding(12.dp)) {
                    hospitalList?.forEach { hospital ->
                        Box(modifier = Modifier.clickable {
                            proceedToDetailPage(navController, hospital)
                        }) {
                            AppointmentRow(hospital)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ShowEmptyState() {
    val image: Painter = painterResource(id = R.drawable.not_found)
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(image, "Img", modifier = Modifier.padding(start = 56.dp, end = 56.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "No Vaccination center is available for booking.",
                style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.W500),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                textAlign = TextAlign.Center
            )
        }
        
    }
}

fun proceedToDetailPage(navController: NavController, hospital: HospitalEntity) {
    navController.navigate("hospital_detail/${hospital.centerId}")
}