package com.ankitdubey021.cowintracker.repository

import com.ankitdubey021.cowintracker.data.AppointmentSessions
import com.ankitdubey021.cowintracker.data.DistrictList
import com.ankitdubey021.cowintracker.data.StateList
import com.ankitdubey021.cowintracker.networking.CowinApiClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CowinRepository constructor(
    private val apiClient: CowinApiClient
) {
    suspend fun fetchStates(): Flow<StateList> = flow {
        try {
            emit(apiClient.getStates())
        }catch (ex : Exception){
            ex.printStackTrace()
        }
    }

    suspend fun fetchDistricts(stateId: Int): Flow<DistrictList> = flow {
        try {
            emit(apiClient.getDistricts(stateId = stateId))
        }catch (ex : Exception){
            ex.printStackTrace()
        }
    }

    suspend fun getAppointmentStatus(dateStr : String, districtId: Int): Flow<AppointmentSessions> = flow {
        try {
            emit(apiClient.getAppointmentByDistrict(districtId, dateStr))
        }catch (ex : Exception){
            ex.printStackTrace()
        }
    }
}