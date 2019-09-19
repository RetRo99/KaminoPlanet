package com.example.kaminoplanet.ViewModel

import android.app.Application
import android.content.Context.MODE_PRIVATE
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.kaminoplanet.Network.LikeResponse
import com.example.kaminoplanet.Network.PlanetInfoResponse
import com.example.kaminoplanet.Model.Repository
import com.example.kaminoplanet.Model.ResidentResponse


class PlanetViewModel(application: Application) : AndroidViewModel(application) {


    private val preferencesName = "planetLiked"
    private val sharedPreferences = application.getSharedPreferences(preferencesName, MODE_PRIVATE)
    var mPlanetId: String? = ""
        set(value) {
            field = value
            repo.loadPlanetInformation(value)
        }
    private val repo = Repository()

    private var pickedResident: MutableLiveData<ResidentResponse> = MutableLiveData()

    fun setPickedResident(resident: ResidentResponse) {
        if(resident.homeworld?.substringAfterLast("/") == mPlanetId)   repo.loadResidentPlanet()    else repo.loadResidentPlanet(resident.homeworld, false)
        pickedResident.postValue(resident)
    }

    fun getPickedResident(): MutableLiveData<ResidentResponse> {
        return pickedResident
    }


    fun planetIsLiked(): Boolean {
        return sharedPreferences.getBoolean(mPlanetId, false)

    }


    fun getPlanet(): MutableLiveData<PlanetInfoResponse> {
        return repo.getPlanet()
    }

    fun getResidentPlanet(): MutableLiveData<PlanetInfoResponse> {
        return repo.getResidentPlanet()
    }



    fun getResidents(): MutableLiveData<List<ResidentResponse>> {
        return repo.getResidents()
    }

    fun getLikeResponse(): MutableLiveData<LikeResponse> {
        return repo.getLikeResponse()
    }

    fun likePlanet() {
        saveCurrentPlanetAsLiked()
        repo.likePlanet(mPlanetId)

    }

    private fun saveCurrentPlanetAsLiked(){
        sharedPreferences.edit().putBoolean(mPlanetId, true).apply()

    }
}