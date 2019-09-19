package com.example.kaminoplanet.Model

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kaminoplanet.R
import com.example.kaminoplanet.Utils.inflate
import com.example.kaminoplanet.Utils.loadCircularImage
import kotlinx.android.synthetic.main.fragment_resident_item.view.*


class ResidentAdapter(
    private var users: List<ResidentResponse>,
    private val clickListener: (ResidentResponse) -> Unit
) :
    RecyclerView.Adapter<ResidentAdapter.ResidentHolder>() {


    override fun getItemCount(): Int {
        return users.size
    }


    override fun onBindViewHolder(holder: ResidentHolder, position: Int) {
        holder.bindUser(users[position], clickListener)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResidentHolder {
        val inflatedView = parent.inflate(R.layout.fragment_resident_item, false)
        return ResidentHolder(inflatedView)
    }


    class ResidentHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v


        fun bindUser(resident: ResidentResponse, clickListener: (ResidentResponse) -> Unit) {

            view.apply {
                residentPhoto.loadCircularImage(resident.imageUrl)
                residentName.text = resident.name
                setOnClickListener { clickListener(resident) }
            }

        }

    }


}


