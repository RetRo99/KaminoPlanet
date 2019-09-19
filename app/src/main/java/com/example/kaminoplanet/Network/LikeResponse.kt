package com.example.kaminoplanet.Network

import com.google.gson.annotations.SerializedName

data class LikeResponse(
    @SerializedName("likes ")
    val likes: Int = 0,
    @SerializedName("planet_id")
    val planetId: Int = 0
)