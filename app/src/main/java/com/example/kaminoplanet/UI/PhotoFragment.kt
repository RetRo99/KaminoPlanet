package com.example.kaminoplanet.UI


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kaminoplanet.R
import com.example.kaminoplanet.Network.ToolbarFragment
import com.example.kaminoplanet.Utils.loadPhotoFromUrl
import kotlinx.android.synthetic.main.fragment_picture.*

private const val ARG_PARAM1 = "param1"

class PhotoFragment : ToolbarFragment() {
    private var photoUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            photoUrl = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_picture, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setToolbar()

        imageView.loadPhotoFromUrl(photoUrl)

    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            PhotoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}
