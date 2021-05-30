package com.ankitdubey021.cowintracker.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ankitdubey021.cowintracker.ui.components.CowinToolbar
import com.ankitdubey021.cowintracker.ui.components.DropdownDemo
import com.ankitdubey021.cowintracker.ui.components.MDatePicker
import com.ankitdubey021.cowintracker.ui.screens.cases_screen.VaccineSlotButton
import com.ankitdubey021.cowintracker.ui.theme.WhiteGray
import com.ankitdubey021.cowintracker.utils.toDateStr
import com.ankitdubey021.cowintracker.utils.toast
import java.util.*


@Composable
fun CowinHome(navController: NavController, homeViewModel: HomeViewModel = viewModel()) {

    val states = homeViewModel.stateLiveData.observeAsState()
    val districts = homeViewModel.districtLiveData.observeAsState()
    var selectedDate = Date()
    var selectedDistrict : Int? = null

    val context = LocalContext.current

    homeViewModel.fetchStates()

    Scaffold(
        topBar = { CowinToolbar{
            navController.navigateUp()
        } }
    ) {
        val stateList = states.value ?: listOf()
        val districtList = districts.value ?: listOf()

        val stateNames = stateList.fold(listOf<String>(), { initialList, entity ->
            initialList + entity.stateName
        }).toMutableList()

        val districtNames = districtList.fold(listOf<String>(), { initialList, entity ->
            initialList + entity.distName
        }).toMutableList()

        stateNames.add(0,"Select")
        districtNames.add(0,"Select")

        Column {

            Box(
                modifier = Modifier
                    .background(MaterialTheme.colors.primary)
            ) {
                Text(
                    text = "Want to check your vaccine slot? You are at the right place!",
                    style = MaterialTheme.typography.h5.copy(color = WhiteGray),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 16.dp, start = 16.dp, end = 8.dp)
                )
            }


            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = "Select State",
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp, bottom = 0.dp, start = 12.dp, end = 8.dp)
                )
            }

            Box(Modifier.padding(12.dp)) {
                DropdownDemo(items = stateNames, onItemSelected = { index ->
                    val selectedState = stateList.firstOrNull {
                        it.stateName == stateNames[index]
                    }
                    selectedState?.stateId?.let {
                        homeViewModel.fetchDistricts(it)
                    }
                })
            }

            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = "Select District",
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp, bottom = 0.dp, start = 12.dp, end = 8.dp)
                )
            }

            Box(Modifier.padding(12.dp)) {
                DropdownDemo(items = districtNames, onItemSelected = { index ->
                    val dist = districtList.firstOrNull {
                        it.distName == districtNames[index]
                    }
                    selectedDistrict = dist?.distId
                })
            }

            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = "Appointment Date",
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp, bottom = 0.dp, start = 12.dp, end = 8.dp)
                )
            }

            Box(Modifier.padding(12.dp)) {
                MDatePicker(dateSelectCallback = {
                    selectedDate = it
                })
            }

            Box(modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 36.dp)){
                VaccineSlotButton("Check Appointment"){
                    if(selectedDistrict == null) {
                        context.toast("Choose District please!")
                        return@VaccineSlotButton
                    }
                    proceedToAppointmentScreen(selectedDate.toDateStr("dd-MM-yyyy"),selectedDistrict ?: -1, navController)
                }
            }
        }
    }
}

fun proceedToAppointmentScreen(date : String, distId : Int, navController: NavController) {
    navController.navigate("appoints?distId=$distId&date=$date")
}

