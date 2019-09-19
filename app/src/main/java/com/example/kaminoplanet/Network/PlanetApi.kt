package com.example.kaminoplanet.Network

import com.example.kaminoplanet.Model.ResidentResponse
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PlanetApi {

    @GET("planets/{planetId}")
    fun getPlanet(@Path("planetId") id: String?): Flowable<PlanetInfoResponse>

    @POST("planets/{planetId}/like")
    fun likePlanet(@Path("planetId") id: String?): Single<LikeResponse>

    @GET("residents/{residentId}")
    fun getResident(@Path("residentId") id: String): Flowable<ResidentResponse>


}
