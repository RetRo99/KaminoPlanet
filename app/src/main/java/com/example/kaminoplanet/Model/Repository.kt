package com.example.kaminoplanet.Model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.kaminoplanet.Network.ApiClient
import com.example.kaminoplanet.Network.LikeResponse
import com.example.kaminoplanet.Network.PlanetInfoResponse
import io.reactivex.Flowable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class Repository {

    private var planetDetail: MutableLiveData<PlanetInfoResponse> = MutableLiveData()

    fun getPlanet(): MutableLiveData<PlanetInfoResponse> {
        return planetDetail
    }

    private var residentPlanet: MutableLiveData<PlanetInfoResponse> = MutableLiveData()

    fun getResidentPlanet(): MutableLiveData<PlanetInfoResponse> {
        return residentPlanet
    }

    private val residents: MutableLiveData<List<ResidentResponse>> = MutableLiveData()

    fun getResidents(): MutableLiveData<List<ResidentResponse>> {
        return residents
    }

    private val likeResponse: MutableLiveData<LikeResponse> = MutableLiveData()

    fun getLikeResponse(): MutableLiveData<LikeResponse> {
        return likeResponse
    }


    fun loadResidentPlanet(planetId:String? = "", isCurrentPlanet:Boolean = true){

        if(isCurrentPlanet){
                residentPlanet = planetDetail

        }     else  {
            val observable =
                ApiClient.getClient.getPlanet(planetId)
            observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribeBy(onNext = {
                    planetDetail.postValue(it)
                })

        }


    }
    fun loadPlanetInformation(planetId: String?) {

        val observable =
            ApiClient.getClient.getPlanet(planetId)

        observable
            .subscribeOn(Schedulers.io())

            .flatMapIterable { it.residents }
            .flatMap {
                loadResident(it.substringAfterLast("/"))
            } // Calls the method which returns a new Observable<Item>
            .observeOn(Schedulers.io())
            .toList()
            .subscribeBy(

                onSuccess = {
                    residents.postValue(it)

                },
                onError = { it.printStackTrace();Log.d("onrerror", "babee") }
            )

        observable.subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribeBy(onNext = {
                planetDetail.postValue(it)
            })


    }


    private fun loadResident(resident: String): Flowable<ResidentResponse> {
        val observer = ApiClient.getClient.getResident(resident)
        return observer
            .subscribeOn(Schedulers.io())
            .map {
                it
            }

    }

    fun likePlanet(planetId: String?) {
        val observer = ApiClient.getClient.likePlanet(planetId)
        observer
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribeBy(onSuccess = {
                likeResponse.postValue(it)
            })

    }


}