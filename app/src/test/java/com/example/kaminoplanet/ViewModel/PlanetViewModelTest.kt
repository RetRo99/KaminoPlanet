package com.example.kaminoplanet.ViewModel

import androidx.test.core.app.ApplicationProvider
import org.apache.maven.model.Repository
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class PlanetViewModelTest {

    private lateinit var model:PlanetViewModel
    private lateinit var repo:Repository

    @Before
    fun setUp() {
        model = PlanetViewModel(ApplicationProvider.getApplicationContext())
        repo = Repository()
        model.mPlanetId = "42"
    }

    @Test
    fun getLikedStatus() {
        model.likePlanet()
        assertEquals(true,model.planetIsLiked())
    }
}