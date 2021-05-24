package com.ankitdubey021.cowintracker.repository

import android.util.Log
import com.ankitdubey021.cowintracker.data.BlogNetworkEntity
import com.ankitdubey021.cowintracker.data.StateList
import com.ankitdubey021.cowintracker.networking.CowinApiClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import retrofit2.Call

class CowinRepository constructor(
    val apiClient: CowinApiClient
){
    suspend fun fetchStates() : Flow<StateList> = flow {
     emit(apiClient.getStates())
    }
}