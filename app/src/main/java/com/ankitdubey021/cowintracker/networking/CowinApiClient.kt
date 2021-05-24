package com.ankitdubey021.cowintracker.networking

import com.ankitdubey021.cowintracker.data.StateList
import retrofit2.http.GET
import retrofit2.http.Headers

interface CowinApiClient {

    @Headers("user-agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36")
    @GET("v2/admin/location/states")
    suspend fun getStates() : StateList
}