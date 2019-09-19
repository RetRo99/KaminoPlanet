package com.example.kaminoplanet.UI


import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.kaminoplanet.*
import com.example.kaminoplanet.Network.LikeResponse
import com.example.kaminoplanet.Network.PlanetInfoResponse
import com.example.kaminoplanet.Network.ToolbarFragment
import com.example.kaminoplanet.Utils.loadCircularImage
import com.example.kaminoplanet.ViewModel.PlanetViewModel
import kotlinx.android.synthetic.main.fragment_planet_detail.*


class PlanetDetailFragment : ToolbarFragment() {
    private lateinit var planetObserver: Observer<PlanetInfoResponse>
    private lateinit var likeResponseObserver: Observer<LikeResponse>
    private lateinit var model: PlanetViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_planet_detail, container, false)
    }

    @SuppressLint("DefaultLocale")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        model = ViewModelProviders.of(activity!!).get(PlanetViewModel::class.java)


        likeResponseObserver = Observer { response ->
            setLikeView(response.likes)

        }

        planetObserver = Observer { planet ->
            setUpLayout(planet)
        }

        model.getPlanet().observe(this, planetObserver)
        model.getLikeResponse().observe(this, likeResponseObserver)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            PlanetDetailFragment()
    }

    @SuppressLint("DefaultLocale")
    private fun setUpLayout(mPlanet:PlanetInfoResponse) {

        setToolBarTitle(mPlanet.name)

        planetImage.loadCircularImage(mPlanet.imageUrl, 4.5f, Color.DKGRAY)
        planetName.text = mPlanet.name.capitalize()
        rotationText.text = mPlanet.rotationPeriod.capitalize()
        orbitalText.text = mPlanet.orbitalPeriod.capitalize()
        diameterText.text = mPlanet.diameter.capitalize()
        climateText.text = mPlanet.climate.capitalize()
        gravityText.text = mPlanet.gravity.capitalize()
        terrainText.text = mPlanet.terrain.capitalize()
        surfaceText.text = mPlanet.surfaceWater.capitalize()
        populationText.text = mPlanet.population.capitalize()

        planetImage.setOnClickListener {
            val manager = (context as AppCompatActivity).supportFragmentManager
            manager
                .beginTransaction()
                .replace(
                    R.id.planetFragment,
                    PhotoFragment.newInstance(mPlanet.imageUrl)
                )
                .addToBackStack(null)
                .commit()
        }

        if (model.planetIsLiked()) {
            setLikeView(mPlanet.likes)

        } else {
            likeButton.setOnClickListener {
                likeButton.isClickable = false
                model.likePlanet()
            }


        }


        holdingLayout.visibility = View.VISIBLE
        progressBar.visibility = View.INVISIBLE
    }

    private fun setLikeView(likes: Int) {
        val liked = "You and $likes people have liked this planet"
        planetLikedTextView.text = liked
        likeButton.visibility = View.INVISIBLE
        planetLikedTextView.visibility = View.VISIBLE

    }
}
