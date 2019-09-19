package com.example.kaminoplanet.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.kaminoplanet.R
import com.example.kaminoplanet.Model.TabAdapter
import kotlinx.android.synthetic.main.fragment_host.*


class HostFragment : Fragment() {
    private lateinit var adapter: PagerAdapter
    private lateinit var viewPager: ViewPager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_host, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewPager = view.findViewById(R.id.pager)

        adapter = TabAdapter(this.childFragmentManager, view.context)
        pager.adapter = adapter

    }


    companion object {

        @JvmStatic
        fun newInstance() =
            HostFragment()
    }

}
