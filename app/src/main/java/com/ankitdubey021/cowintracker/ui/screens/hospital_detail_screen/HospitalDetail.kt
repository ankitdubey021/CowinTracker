package com.ankitdubey021.cowintracker.ui.screens.hospital_detail_screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ankitdubey021.cowintracker.R
import com.ankitdubey021.cowintracker.data.chartColors
import com.ankitdubey021.cowintracker.ui.components.MBackIcon
import com.ankitdubey021.cowintracker.ui.screens.home.HomeViewModel
import com.ankitdubey021.cowintracker.ui.theme.Pink
import com.ankitdubey021.cowintracker.ui.theme.Red
import com.ankitdubey021.cowintracker.ui.theme.WhiteGray
import com.ankitdubey021.cowintracker.utils.toDate
import com.ankitdubey021.cowintracker.utils.toDateStr
import java.util.*

@Composable
fun HospitalDetail(
    navController: NavController,
    homeViewModel: HomeViewModel,
    hospitalId: String?
) {

    val hospitalDao = homeViewModel.appointments.value?.firstOrNull {
        it.centerId == hospitalId?.toInt()
    }

    val image: Painter = painterResource(id = R.drawable.medicines)
    var addrs = ""
    hospitalDao?.let {
        addrs = listOf(it.address, it.distName, it.stateName, it.pincode).joinToString(", ")
    }

    Scaffold(
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Brush.verticalGradient(listOf(WhiteGray, Color.White)))
        ) {
            Box(
                modifier = Modifier
                    .weight(1.2F)
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                MBackIcon {
                    navController.navigateUp()
                }
                HeaderImage(image)
            }
            Box(
                modifier = Modifier
                    .weight(1F)
                    .fillMaxSize()
                    .padding(start = 10.dp, end = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        hospitalDao?.name ?: "",
                        style = MaterialTheme.typography.h3,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        addrs,
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(4F)
                    .padding(start = 12.dp, end = 12.dp)
                    .clip(
                        CircleShape.copy(
                            topStart = CornerSize(20.dp),
                            topEnd = CornerSize(20.dp),
                            bottomStart = CornerSize(0.dp),
                            bottomEnd = CornerSize(0.dp)
                        )
                    ),
                backgroundColor = Color.White
            ) {
                Column {
                    Text(
                        text = "Covaxin",
                        style = MaterialTheme.typography.h2,
                        modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    )
                    Spacer(
                        modifier = Modifier
                            .height(16.dp)
                            .padding(start = 16.dp, end = 16.dp)
                    )
                    DoseCounter(hospitalDao?.availableCapacityDose1?:0, hospitalDao?.availableCapacityDose2 ?:0)
                    HospitalDetailDivider()
                    TimingSlotView(hospitalDao?.slots?: listOf())
                    HospitalDetailDivider()
                    DateAndAgeCounter(hospitalDao?.date?.toDate("dd-MM-yyyy")?.toDateStr("dd MMM") ?: Date().toDateStr("dd MMM"), hospitalDao?.minAgeLimit?:0)
                }
            }
        }
    }
}

@Composable
private fun TimingSlotView(slots : List<String>) {
    Text(
        text = "Timing Slots",
        style = MaterialTheme.typography.body1,
        modifier = Modifier.padding(start = 16.dp)
    )

    Row(Modifier.horizontalScroll(rememberScrollState())) {
        slots.forEach {
            Card(
                backgroundColor = WhiteGray,
                modifier = Modifier
                    .padding(6.dp)
                    .clip(CircleShape.copy(CornerSize(18.dp)))
            ) {
                Text(
                    text = it,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp)
                )
            }
        }
    }
}

@Composable
private fun DateAndAgeCounter(date : String, age : Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.weight(1F), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Date", style = MaterialTheme.typography.body1)
                Text(text = date, style = MaterialTheme.typography.h2)
            }
        }
        Spacer(
            modifier = Modifier
                .width(1.dp)
                .height(40.dp)
                .background(Color.LightGray)
        )
        Box(modifier = Modifier.weight(1F), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Age Limit", style = MaterialTheme.typography.body1)
                Text(text = "$age+", style = MaterialTheme.typography.h2)
            }
        }
    }
}

@Composable
private fun HospitalDetailDivider() {
    Spacer(modifier = Modifier.height(24.dp))
    Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        Divider()
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
private fun DoseCounter(dose1: Int, dose2: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1F),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = dose1.toString(), style = MaterialTheme.typography.h1.copy(color = if(dose1 == 0) Red else Color.Black))
                Text(text = "Dose 1", style = MaterialTheme.typography.body1)
            }
        }
        Spacer(
            modifier = Modifier
                .width(1.dp)
                .height(40.dp)
                .background(Color.LightGray)
        )
        Box(
            modifier = Modifier
                .weight(1F),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = dose2.toString(), style = MaterialTheme.typography.h1.copy(color = if(dose2 == 0) Red else Color.Black))
                Text(text = "Dose 2", style = MaterialTheme.typography.body1)
            }
        }
    }
}

@Composable
private fun HeaderImage(image: Painter) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Image(
            image, "Img",
            modifier =
            Modifier
                .width(100.dp)
                .height(100.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
        )
    }
}