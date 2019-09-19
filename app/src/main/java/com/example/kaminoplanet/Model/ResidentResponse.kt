package com.example.kaminoplanet.Model

import com.google.gson.annotations.SerializedName

data class ResidentResponse(
    @SerializedName("homeworld")
    val homeworld: String? = "",
    @SerializedName("eye_color")
    val eyeColor: String? = "",
    @SerializedName("gender")
    val gender: String? = "",
    @SerializedName("skin_color")
    val skinColor: String? = "",
    @SerializedName("edited")
    val edited: String? = "",
    @SerializedName("created")
    val created: String? = "",
    @SerializedName("image_url")
    val imageUrl: String? = "",
    @SerializedName("mass")
    val mass: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("height")
    val height: String? = "",
    @SerializedName("hair_color")
    val hairColor: String? = "",
    @SerializedName("birth_year")
    val birthYear: String? = ""
)