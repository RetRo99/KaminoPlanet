package com.example.kaminoplanet.UI


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kaminoplanet.ViewModel.PlanetViewModel
import com.example.kaminoplanet.R
import com.example.kaminoplanet.Model.ResidentAdapter
import com.example.kaminoplanet.Model.ResidentResponse
import kotlinx.android.synthetic.main.fragment_residents.*


class ResidentsListFragment : Fragment() {
    private lateinit var observer: Observer<List<ResidentResponse>>
    private lateinit var adapter: ResidentAdapter
    private lateinit var model: PlanetViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_residents, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        model = ViewModelProviders.of(activity!!).get(PlanetViewModel::class.java)



        observer = Observer { residents ->


            adapter =
                ResidentAdapter(residents) { resident: ResidentResponse ->
                    residentClicked(
                        resident
                    )
                }
            val linearLayoutManager = LinearLayoutManager(view.context)
            recycleViewResidents.layoutManager = linearLayoutManager
            recycleViewResidents.adapter = adapter


            progressBarResidents.visibility = View.INVISIBLE
        }


        model.getResidents().observe(this, observer)


    }

    private fun residentClicked(resident: ResidentResponse) {
        val manager = (context as AppCompatActivity).supportFragmentManager
        model.setPickedResident(resident)

        manager
            .beginTransaction()
            .replace(
                R.id.planetFragment,
                ResidentDetailsFragment.newInstance()
            )
            .addToBackStack(null)
            .commit()
    }


    companion object {

        @JvmStatic
        fun newInstance() =
            ResidentsListFragment()
    }


}
