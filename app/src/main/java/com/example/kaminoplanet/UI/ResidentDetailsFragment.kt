package com.example.kaminoplanet.UI

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.kaminoplanet.Model.ResidentResponse
import com.example.kaminoplanet.Network.PlanetInfoResponse
import com.example.kaminoplanet.Network.ToolbarFragment
import com.example.kaminoplanet.R
import com.example.kaminoplanet.Utils.loadCircularImage
import com.example.kaminoplanet.ViewModel.PlanetViewModel
import kotlinx.android.synthetic.main.fragment_resident_details.*


class ResidentDetailsFragment : ToolbarFragment() {
    private lateinit var observer: Observer<ResidentResponse>
    private lateinit var model: PlanetViewModel
    private lateinit var planetObserver: Observer<PlanetInfoResponse>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resident_details, container, false)
    }

    @SuppressLint("DefaultLocale")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        model = ViewModelProviders.of(activity!!).get(PlanetViewModel::class.java)
        setToolbar()
        planetObserver = Observer { planet ->
            residentDetailLocation.text = planet.name
        }


        observer = Observer { resident ->
            setToolBarTitle(resident.name)
            setupLayout(resident)



        }
        model.getPickedResident().observe(this, observer)
        model.getResidentPlanet().observe(this, planetObserver)


    }

    @SuppressLint("DefaultLocale")
    private fun setupLayout(resident:ResidentResponse) {
        residentDetailPhoto.loadCircularImage(resident.imageUrl, 2.5f, Color.BLACK)
        residentDetailName.text = resident.name?.capitalize()
        residentDetailBirth.text = resident.birthYear?.capitalize()
        residentDetailEye.text = resident.eyeColor?.capitalize()
        residentDetailGender.text = resident.gender?.capitalize()
        residentDetailMass.text = resident.mass?.capitalize()
        residentDetailHair.text = resident.hairColor?.capitalize()
        residentDetailHeight.text = resident.height?.capitalize()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ResidentDetailsFragment()
    }
}

