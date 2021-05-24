package com.ankitdubey021.cowintracker.data

import com.google.gson.annotations.SerializedName

data class StateList(
    val states : List<StateEntity>
)
data class StateEntity(
    @SerializedName("state_id")
    val stateId : Int,

    @SerializedName("state_name")
    val stateName : String
)