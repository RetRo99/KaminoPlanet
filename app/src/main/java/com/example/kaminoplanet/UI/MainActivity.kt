package com.example.kaminoplanet.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.kaminoplanet.ViewModel.PlanetViewModel
import com.example.kaminoplanet.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewModelProviders.of(this).get(PlanetViewModel::class.java).mPlanetId = "10"


        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.planetFragment,
                HostFragment.newInstance(), "hostFragment")
            .disallowAddToBackStack()
            .commit()

    }
}
