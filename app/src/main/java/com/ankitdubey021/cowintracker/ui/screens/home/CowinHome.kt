package com.ankitdubey021.cowintracker.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ankitdubey021.cowintracker.ui.screens.home.HomeViewModel


@Composable
fun CowinHome(homeViewModel: HomeViewModel = viewModel()){

    val states = homeViewModel.stateLiveData.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Cowin Tracker",style = MaterialTheme.typography.h3)
                },
                backgroundColor = MaterialTheme.colors.primary,
            )
        }
    ) {
        Column() {
            states.value?.forEach {
                Text(text = "${it.stateId} ${it.stateName}",style = MaterialTheme.typography.h1)
            }
        }
    }
}