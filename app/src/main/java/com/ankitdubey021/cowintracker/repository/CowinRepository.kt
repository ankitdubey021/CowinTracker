package com.ankitdubey021.cowintracker.repository

import com.ankitdubey021.cowintracker.data.AppointmentSessions
import com.ankitdubey021.cowintracker.data.DistrictList
import com.ankitdubey021.cowintracker.data.StateList
import com.ankitdubey021.cowintracker.networking.CowinApiClient
import com.ankitdubey021.cowintracker.utils.toDateStr
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

class CowinRepository constructor(
    private val apiClient: CowinApiClient
) {
    suspend fun fetchStates(): Flow<StateList> = flow {
        emit(apiClient.getStates())
    }

    suspend fun fetchDistricts(stateId: Int): Flow<DistrictList> = flow {
        emit(apiClient.getDistricts(stateId = stateId))
    }

    suspend fun getAppointmentStatus(dateStr : String, districtId: Int): Flow<AppointmentSessions> = flow {
        emit(apiClient.getAppointmentByDistrict(districtId, dateStr))
    }
}