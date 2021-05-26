package com.ankitdubey021.cowintracker.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ankitdubey021.cowintracker.ui.theme.CowinTrackerTheme

@Composable
fun DropdownDemo(items: MutableList<String>, onItemSelected: (Int) -> Unit) {

    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }

    val backgroundColor = if (MaterialTheme.colors.isLight) {
        MaterialTheme.colors.onSurface.copy(alpha = 0.06f)
    } else {
        MaterialTheme.colors.onSurface.copy(alpha = 0.06f)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopStart)

    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = backgroundColor,
                    shape = MaterialTheme.shapes.small
                )
                .clickable {
                    if (items.size > 1)
                        expanded = true
                }
        ) {
            Text(
                text = if (items.size < 2) "" else items[selectedIndex],
                style = MaterialTheme.typography.subtitle1.copy(color = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 18.dp, horizontal = 16.dp)
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    if (index != 0) {
                        selectedIndex = index
                        onItemSelected(index)
                        expanded = false
                    }
                }) {
                    Text(
                        text = s,
                        style = TextStyle(color = if (index == 0) Color.Gray else Color.Black)
                    )
                }
            }
        }
    }
}

@Composable
fun CowinToolbar(onBackClicked: (() -> Unit)? = null) {

    TopAppBar(
        title = {
            Text(text = "Cowin Tracker", style = MaterialTheme.typography.h3)
        },
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.primary,
        navigationIcon = if (onBackClicked == null) null else ({
            IconButton(onClick = onBackClicked) {
                Icon(Icons.Filled.ArrowBack, "back")
            }
        })
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CowinTrackerTheme {
        DropdownDemo(mutableListOf()) {}
    }
}