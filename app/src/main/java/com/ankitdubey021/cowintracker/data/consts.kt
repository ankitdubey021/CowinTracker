package com.ankitdubey021.cowintracker.data

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.ankitdubey021.cowintracker.ui.theme.Magenta
import com.ankitdubey021.cowintracker.ui.theme.WhiteGray
import com.google.gson.annotations.SerializedName


data class BlogNetworkEntity(
    @SerializedName("pk")
    val id: Int,
    val title: String,
    val body: String,
    val image: String,
    val category: String
)

fun List<ChartStateDao>.extractProportion(): List<Float> {
    val total = this.sumByDouble {
        it.activeCase.toDouble()
    }

    println("mummy $total")

    return this.map {
        (it.activeCase / total.toFloat())
    }
}


data class ChartStateDao(
    val stateName: String,
    val activeCase: Long,
    val color: Color
)


val chartColors = listOf(
    Magenta,
    Color(0xFF583d72),
    Color(0xFF9f5f80),
    Color(0xFFFF6951),
    Color(0xFFff8474),
    Color(0xFFFFAC12),
    Color(0xFFFFDC78),
    Color(0xFFffc996),
    WhiteGray,
)
