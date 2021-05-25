package com.ankitdubey021.cowintracker.utils

import java.text.SimpleDateFormat
import java.util.*

infix fun String.toDate(format: String): Date {
    return try {
        val sdf = SimpleDateFormat(format, Locale.ENGLISH)
        sdf.timeZone = TimeZone.getTimeZone("Asia/Dubai")
        sdf.parse(this)!!
    } catch (ex: Exception) {
        Date()
    }
}

infix fun Date.toDateStr(format: String): String {
    return try {
        val sdf = SimpleDateFormat(format, Locale.ENGLISH)
        sdf.timeZone = TimeZone.getTimeZone("Asia/Dubai")
        sdf.format(this)
    } catch (ex: Exception) {
        ""
    }
}