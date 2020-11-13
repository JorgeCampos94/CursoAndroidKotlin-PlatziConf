package com.example.platzi.view.ui.fragments.speakersFragment

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.platzi.R
import com.example.platzi.models.Conferences
import com.example.platzi.models.Speakers
import com.example.platzi.utility.setGlide
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.*
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.toolbar_conference
import kotlinx.android.synthetic.main.fragment_speakers_detail_dialog.*

class SpeakersDetailDialogFragment : DialogFragment(){

    lateinit var speakers: Speakers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speakers_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        speakers = arguments?.getSerializable("speaker") as Speakers
        setContentToolbar()
        setBodyDialogConference()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    private fun setContentToolbar() {
        toolbar_speakers.navigationIcon = view?.context?.let { ContextCompat.getDrawable(it, R.drawable.ic_close) }
        toolbar_speakers.setTitleTextColor(Color.WHITE)
        toolbar_speakers.setNavigationOnClickListener {
            dismiss()
        }

        toolbar_speakers.title = speakers.name
    }

    private fun setBodyDialogConference() {
        context?.let {mContext ->
            img_speaker_speaker_detail_dialog.setGlide(mContext, speakers.image , true)
            tv_name_speaker_detail_dialog.text = speakers.name
            tv_job_speaker_detail_dialog.text = speakers.jobTitle
            tv_company_speaker_detail_dialog.text = speakers.workPlace
            iv_twitter_link_speaker_detail_dialog.setOnClickListener {
                goToTwitter(speakers.twitter)
            }
            tv_description_speaker_detail_dialog.text = speakers.biography
        }
    }

    private fun goToTwitter(nickname: String) {
        val openURL = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("https://twitter.com/$nickname")
        }
        startActivity(openURL)
    }
}