package com.ankitdubey021.cowintracker.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class StateList(
    val states: List<StateEntity>
)

data class StateEntity(
    @SerializedName("state_id")
    val stateId: Int,

    @SerializedName("state_name")
    val stateName: String
)

data class DistrictList(
    val districts: List<DistrictEntity>
)

data class DistrictEntity(
    @SerializedName("district_id")
    val distId: Int,

    @SerializedName("district_name")
    val distName: String
)

data class AppointmentSessions(
    val sessions: List<HospitalEntity>
)

data class HospitalEntity(
    @SerializedName("center_id")
    val centerId: Int,

    val name: String,
    val address: String,
    @SerializedName("state_name")
    val stateName: String,

    @SerializedName("district_name")
    val distName: String,

    @SerializedName("block_name")
    val blocName: String,
    val pincode: Int,
    val from: String,
    val to: String,
    val date: String,
    val vaccine: String,
    val slots : List<String>,

    @SerializedName("available_capacity_dose1")
    val availableCapacityDose1: Int,

    @SerializedName("available_capacity_dose2")
    val availableCapacityDose2: Int,
    @SerializedName("min_age_limit")
    val minAgeLimit : Int
):Serializable

data class CovidReportDao(
    val cases_time_series : List<CaseReportEntity>,
    val statewise : List<CaseReportStateWise>
)

data class CaseReportEntity(
    @SerializedName("dailyconfirmed")
    val dailyConfirmed: String,

    @SerializedName("dailyrecovered")
    val dailyRecovered: String,

    @SerializedName("totalconfirmed")
    val totalCases: String
)

data class CaseReportStateWise(
    val active : String,
    val confirmed : String,
    val deaths : String,
    val recovered : String,
    val state : String,
)
