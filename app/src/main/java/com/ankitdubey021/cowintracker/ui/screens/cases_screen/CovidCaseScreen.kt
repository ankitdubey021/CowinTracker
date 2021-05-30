package com.ankitdubey021.cowintracker.ui.screens.cases_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ankitdubey021.cowintracker.R
import com.ankitdubey021.cowintracker.data.*
import com.ankitdubey021.cowintracker.ui.theme.Magenta
import com.ankitdubey021.cowintracker.ui.theme.WhiteGray
import com.ankitdubey021.cowintracker.utils.AnimatedCircle
import com.ankitdubey021.cowintracker.utils.formatNumber

@Composable
fun CovidCase(navController: NavController, viewModel: CovidReportViewModel) {

    val covidReport = viewModel.liveData.observeAsState()

    val caseReport: List<CaseReportEntity> = covidReport.value?.cases_time_series ?: listOf()
    val stateWiseReport = covidReport.value?.statewise
    val latestReport = caseReport.lastOrNull()

    Scaffold(
    ) {
        Column {
            HeaderView()
            Spacer(modifier = Modifier.height(12.dp))
            Box(Modifier.padding(start = 12.dp, end = 12.dp)) {
                VaccineSlotButton("Check your vaccine slot"){
                    navController.navigate("home")
                }
            }
            CaseSummary(latestReport)
            if (!stateWiseReport.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(18.dp))
                StateWiseReport(stateWiseReport, latestReport?.totalCases)
            }
        }
    }
}

@Composable
fun CaseSummary(caseReport : CaseReportEntity?){
    Row(Modifier.padding(start = 12.dp, end = 12.dp, top = 18.dp)) {
        Box(modifier = Modifier.weight(1F)) {
            InformationCard(
                "New Cases",
                if (caseReport == null) "N/A" else formatNumber(caseReport.dailyConfirmed.toLong())
            )
        }
        Box(modifier = Modifier.weight(1F)) {
            InformationCard(
                "New Recovered",
                if (caseReport == null) "N/A" else formatNumber(caseReport.dailyRecovered.toLong())
            )
        }
        Box(modifier = Modifier.weight(1F)) {
            InformationCard(
                "Total Cases",
                if (caseReport == null) "N/A" else formatNumber(caseReport.totalCases.toLong())
            )
        }
    }
}

@Composable
fun StateWiseReport(reportStateWise: List<CaseReportStateWise>, totalCases : String?) {

    val sortedReport = reportStateWise.sortedBy {
        it.active.toLong()
    }.reversed()

    val chartStateDaoList = mutableListOf<ChartStateDao>()

    for(i in 1..8){
        val state = sortedReport[i]
        val chartStateDao = ChartStateDao(
            stateName = state.state,
            activeCase = state.active.toLong(),
            color = chartColors[i-1]
        )
        chartStateDaoList.add(chartStateDao)
    }

    var sumOfTopFourStateCase = 0L
    chartStateDaoList.fold(sumOfTopFourStateCase, { initialSum, dao ->
        initialSum + dao.activeCase
    }).let {
        sumOfTopFourStateCase = it
    }

    val sumOfRestStateCases : Long = sortedReport.first().active.toLong() - sumOfTopFourStateCase

    val chartStateDao = ChartStateDao(
        stateName = "Others",
        activeCase = sumOfRestStateCases,
        color = chartColors.last()
    )
    chartStateDaoList.add(chartStateDao)

    val total = totalCases?:sortedReport.last().active

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.weight(1F)) {
            ChartDetailView(items = chartStateDaoList)
        }
        Box(
            modifier = Modifier
                .weight(1F)
                .padding(end = 16.dp, top = 4.dp)

        ) {
            ChartView(total, chartStateDaoList)
        }

    }
}

@Composable
fun ChartDetailView(items : List<ChartStateDao>){
    Column (Modifier.padding(16.dp)){
        Text(text = "State wise cases", style = MaterialTheme.typography.caption.copy(fontSize = 16.sp))
        Spacer(modifier = Modifier.height(8.dp))
        items.forEach { dao ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(modifier = Modifier
                    .width(12.dp)
                    .height(12.dp)
                    .clip(CircleShape)
                    .background(dao.color)

                ) {

                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = dao.stateName, style = MaterialTheme.typography.subtitle1.copy(color = Color.Black))
            }

        }
    }
}

@Composable
fun ChartView(totalCases : String, items : List<ChartStateDao>) {
    val amountProportion = items.extractProportion()
    val circleColors = items.map { it.color }

    Box {
        AnimatedCircle(
            amountProportion,
            circleColors,
            Modifier
                .height(200.dp)
                .align(Alignment.Center)
                .fillMaxWidth()
        )
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(
                text = "Total Active",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = formatNumber(totalCases.toLong()),
                style = MaterialTheme.typography.h2,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun VaccineSlotButton(title : String, callBack: () -> Unit) {
    Button(
        onClick = callBack,
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth()
            .border(1.dp, Color.White, shape = CircleShape)
            .clip(CircleShape),
        colors = ButtonDefaults.buttonColors(backgroundColor = WhiteGray)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.weight(1F)
            )
            Icon(Icons.Filled.ChevronRight, "CTA", tint = Color.DarkGray)
        }
    }
}

@Composable
fun HeaderView() {

    val image: Painter = painterResource(id = R.drawable.taj)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalAlignment = Alignment.End
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Image(
            image, "Taj",
            modifier =
            Modifier
                .width(60.dp)
                .height(60.dp)
                .clip(CircleShape)
                .border(4.dp, Color.LightGray, CircleShape),
            contentScale = ContentScale.Crop,
        )
        Text(
            text = "Wear mask",
            style = MaterialTheme.typography.h2.copy(color = Magenta),
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Stay Home, Stay Safe",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun InformationCard(title: String, desc: String) {
    Box(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .height(80.dp)
            .clip(CircleShape.copy(CornerSize(20.dp)))
            .background(MaterialTheme.colors.onSurface.copy(alpha = 0.06f)),
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = title, style = MaterialTheme.typography.caption)
            Text(text = desc, style = MaterialTheme.typography.body1)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MPreview() {
    HeaderView()
}