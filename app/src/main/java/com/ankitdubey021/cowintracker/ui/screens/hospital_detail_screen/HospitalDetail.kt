package com.ankitdubey021.cowintracker.ui.screens.hospital_detail_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
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
import com.ankitdubey021.cowintracker.ui.components.MBackIcon
import com.ankitdubey021.cowintracker.ui.screens.home.HomeViewModel
import com.ankitdubey021.cowintracker.ui.theme.WhiteGray

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
                        hospitalDao?.name?:"",
                        style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.W500),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        addrs,
                        style = MaterialTheme.typography.body2,
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