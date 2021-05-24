package com.ankitdubey021.cowintracker.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ankitdubey021.cowintracker.R

// Set of Material typography styles to start with
private val MontSerrat = FontFamily(
    Font(R.font.montserrat_bold, FontWeight.W600),
    Font(R.font.montserrat_medium, FontWeight.W500),
    Font(R.font.montserrat_light, FontWeight.W400),
    Font(R.font.montserrat_regular, FontWeight.W300)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = MontSerrat,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        fontFamily = MontSerrat,
        fontWeight = FontWeight.W500,
        fontSize = 30.sp
    ),
    h2 = TextStyle(
        fontFamily = MontSerrat,
        fontWeight = FontWeight.W500,
        fontSize = 24.sp
    ),
    h3 = TextStyle(
        fontFamily = MontSerrat,
        fontWeight = FontWeight.W500,
        fontSize = 20.sp
    ),
    h4 = TextStyle(
        fontFamily = MontSerrat,
        fontWeight = FontWeight.W400,
        fontSize = 18.sp
    ),
    h5 = TextStyle(
        fontFamily = MontSerrat,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp
    ),
    h6 = TextStyle(
        fontFamily = MontSerrat,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = MontSerrat,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = MontSerrat,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp
    ),
    body2 = TextStyle(
        fontFamily = MontSerrat,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = MontSerrat,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    button = TextStyle(
        fontFamily = MontSerrat,
        fontWeight = FontWeight.W400,
        fontSize = 15.sp,
        color = Color.White
    ),
)