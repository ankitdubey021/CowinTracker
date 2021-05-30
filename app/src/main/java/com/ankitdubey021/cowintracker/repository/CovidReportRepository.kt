package com.ankitdubey021.cowintracker.repository

import com.ankitdubey021.cowintracker.data.CovidReportDao
import com.ankitdubey021.cowintracker.networking.CovidReportApiClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class CovidReportRepository constructor(
    private val apiClient: CovidReportApiClient
) {
    suspend fun getReport(): Flow<CovidReportDao> = flow {
        try {
            emit(apiClient.getCovidReport())
        }catch (ex : Exception){
            ex.printStackTrace()
        }
    }
}