package com.example.platzi.view.ui.fragments.locationFragment

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.platzi.R
import com.example.platzi.models.Location
import com.example.platzi.utility.setGlide
import kotlinx.android.synthetic.main.fragment_location_detail_dialog.*

class LocationDetailDialogFragment : DialogFragment() {
    private val location = Location()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_location_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setContentToolbar()
        setBodyDialogConference()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    private fun setContentToolbar() {
        toolbar_location.navigationIcon = view?.context?.let { ContextCompat.getDrawable(it, R.drawable.ic_close) }
        toolbar_location.setTitleTextColor(Color.WHITE)
        toolbar_location.title = location.nameUbication
        toolbar_location.setNavigationOnClickListener {
            dismiss()
        }
    }

    private fun setBodyDialogConference() {
        context?.let { mContext ->
            img_platzi_location_detail_dialog.setGlide(mContext, location.photo, false)
            tv_name_ubication_location_detail_dialog.text = location.nameUbication
            tv_addres_conference_location_detail_dialog.text = location.address
            tv_number_phone_location_detail_dialog.text = location.phone
            tv_website_location_detail_dialog.text = location.website

            tv_number_phone_location_detail_dialog.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:${location.phone}")
                }
                startActivity(intent)
            }
            tv_website_location_detail_dialog.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(location.website)
                startActivity(intent)
            }
        }
    }
}