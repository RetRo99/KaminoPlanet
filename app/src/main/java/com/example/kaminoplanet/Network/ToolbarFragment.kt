package com.example.kaminoplanet.Network

import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.kaminoplanet.R

abstract class ToolbarFragment : Fragment() {


    fun setToolbar() {
        val toolbar: Toolbar = activity!!.findViewById(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_back_button)
        toolbar.setNavigationOnClickListener {
            toolbar.navigationIcon = null
            activity!!.onBackPressed()
        }
    }

    fun setToolBarTitle(title:String?){
        val toolbar: Toolbar = activity!!.findViewById(R.id.toolbar)
        toolbar.title = title
    }


}