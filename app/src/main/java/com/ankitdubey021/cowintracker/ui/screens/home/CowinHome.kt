package com.ankitdubey021.cowintracker.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ankitdubey021.cowintracker.ui.CowinToolbar
import com.ankitdubey021.cowintracker.ui.DropdownDemo
import com.ankitdubey021.cowintracker.ui.theme.WhiteGray


@Composable
fun CowinHome(navController: NavController, homeViewModel: HomeViewModel = viewModel()) {

    val states = homeViewModel.stateLiveData.observeAsState()
    val districts = homeViewModel.districtLiveData.observeAsState()

    Scaffold(
        topBar = { CowinToolbar() }
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
                    val selectedDistrict = districtList.firstOrNull {
                        it.distName == districtNames[index]
                    }
                    selectedDistrict?.distId?.let {
                        proceedToAppointmentScreen(it, navController = navController)
                    }
                })
            }
        }
    }
}

fun proceedToAppointmentScreen(distId : Int, navController: NavController) {
    navController.navigate("appoints/$distId")
}

