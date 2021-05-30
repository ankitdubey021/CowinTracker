package com.ankitdubey021.cowintracker.networking

import com.ankitdubey021.cowintracker.data.CovidReportDao
import retrofit2.http.GET

interface CovidReportApiClient {

    @GET("data.json")
    suspend fun getCovidReport() : CovidReportDao

}