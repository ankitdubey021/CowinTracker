package com.ankitdubey021.cowintracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ankitdubey021.cowintracker.data.HospitalEntity
import com.ankitdubey021.cowintracker.ui.screens.appointment.AppointmentScreen
import com.ankitdubey021.cowintracker.ui.screens.cases_screen.CovidCase
import com.ankitdubey021.cowintracker.ui.screens.cases_screen.CovidReportViewModel
import com.ankitdubey021.cowintracker.ui.screens.home.CowinHome
import com.ankitdubey021.cowintracker.ui.screens.home.HomeViewModel
import com.ankitdubey021.cowintracker.ui.screens.hospital_detail_screen.HospitalDetail
import com.ankitdubey021.cowintracker.ui.theme.CowinTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CowinTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ComposeNavigation()
                }
            }
        }
    }
}

@Composable
fun ComposeNavigation() {
    val navController = rememberNavController()

    val homeViewModel = hiltViewModel<HomeViewModel>()

    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            val viewModel = hiltViewModel<CovidReportViewModel>()
            CovidCase(navController, viewModel)
            /*HospitalDetail(
                navController = navController,
                homeViewModel,
                ""
            )*/
        }
        composable("home") {
            CowinHome(navController, homeViewModel)
        }
        composable("appoints?distId={dist}&date={sDate}") { backStackEntry ->
            AppointmentScreen(
                navController = navController,
                homeViewModel,
                backStackEntry.arguments?.getString("dist"),
                backStackEntry.arguments?.getString("sDate")
            )
        }

        composable("hospital_detail/{hospital_id}") { backStackEntry ->
            HospitalDetail(
                navController = navController,
                homeViewModel,
                backStackEntry.arguments?.getString("hospital_id")
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CowinTrackerTheme {
    }
}