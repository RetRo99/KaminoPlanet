package com.example.kaminoplanet.Network

import com.google.gson.annotations.SerializedName

data class PlanetInfoResponse(
    @SerializedName("edited")
    val edited: String = "",
    @SerializedName("created")
    val created: String = "",
    @SerializedName("image_url")
    val imageUrl: String = "",
    @SerializedName("climate")
    val climate: String = "",
    @SerializedName("rotation_period")
    val rotationPeriod: String = "",
    @SerializedName("population")
    val population: String = "",
    @SerializedName("orbital_period")
    val orbitalPeriod: String = "",
    @SerializedName("surface_water")
    val surfaceWater: String = "",
    @SerializedName("diameter")
    val diameter: String = "",
    @SerializedName("gravity")
    val gravity: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("residents")
    val residents: List<String>?,
    @SerializedName("terrain")
    val terrain: String = "",
    @SerializedName("likes")
    val likes: Int = 0
)