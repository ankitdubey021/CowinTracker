package com.ankitdubey021.cowintracker.ui.screens

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.ankitdubey021.cowintracker.ui.theme.Magenta


@Composable
fun CowinHome(){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Cowin Tracker",style = MaterialTheme.typography.h3)
                },
                backgroundColor = Magenta,
                /*actions = {
                    IconButton(onClick = {
                        toggleDarkMode(isDark)
                    }) {
                        Icon(if (isDark) Icons.Filled.LightMode else Icons.Filled.DarkMode, "Menu")
                    }
                },*/
            )
        }
    ) {

    }
}