package com.ankitdubey021.cowintracker.networking

import com.ankitdubey021.cowintracker.data.AppointmentSessions
import com.ankitdubey021.cowintracker.data.DistrictList
import com.ankitdubey021.cowintracker.data.StateList
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface CowinApiClient {

    @Headers("user-agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36")
    @GET("v2/admin/location/states")
    suspend fun getStates() : StateList

    @Headers("user-agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36")
    @GET("v2/admin/location/districts/{state_id}")
    suspend fun getDistricts(@Path("state_id") stateId : Int) : DistrictList

    @Headers("user-agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36")
    @GET("v2/appointment/sessions/public/findByDistrict")
    suspend fun getAppointmentByDistrict(
        @Query("district_id") districtId : Int,
        @Query("date") date : String
    ) : AppointmentSessions
}