package com.example.kaminoplanet.Model

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.kaminoplanet.R
import com.example.kaminoplanet.UI.PlanetDetailFragment
import com.example.kaminoplanet.UI.ResidentsListFragment


class TabAdapter(fm: FragmentManager, private val context: Context) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


    private val TAB_TITLES = arrayOf(
        R.string.tab_label1,
        R.string.tab_label2
    )


    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> PlanetDetailFragment.newInstance()
            1 -> ResidentsListFragment.newInstance()
            else -> PlanetDetailFragment.newInstance()
        }
    }


    override fun getCount(): Int {
        return TAB_TITLES.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }
}