package com.ankitdubey021.cowintracker.ui.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun CowinToolbar(title : String = "Cowin Tracker", onBackClicked: (() -> Unit)? = null) {

    TopAppBar(
        title = {
            Text(text = title, style = MaterialTheme.typography.h3)
        },
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.primary,
        navigationIcon = if (onBackClicked == null) null else ({
            MBackIcon(onBackClicked)
        })
    )
}

@Composable
fun MBackIcon(onPressed : () -> Unit){
    IconButton(onClick = onPressed) {
        Icon(Icons.Filled.ArrowBack, "back")
    }
}