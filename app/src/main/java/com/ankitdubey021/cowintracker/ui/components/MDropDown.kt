package com.ankitdubey021.cowintracker.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 18.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = if (items.size < 2) "" else items[selectedIndex],
                    style = MaterialTheme.typography.subtitle1.copy(color = Color.Black),
                )
                Icon(Icons.Filled.KeyboardArrowDown,"calendar", tint = Color.DarkGray)
            }
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


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CowinTrackerTheme {
        DropdownDemo(mutableListOf()) {}
    }
}