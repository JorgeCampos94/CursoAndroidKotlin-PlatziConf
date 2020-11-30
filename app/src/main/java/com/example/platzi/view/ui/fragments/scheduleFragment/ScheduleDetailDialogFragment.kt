package com.example.platzi.view.ui.fragments.scheduleFragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.platzi.R
import com.example.platzi.models.Conferences
import com.example.platzi.utility.setGlide
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.*
import java.text.SimpleDateFormat

class ScheduleDetailDialogFragment : DialogFragment() {

    lateinit var conferences: Conferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schedule_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        conferences = arguments?.getSerializable("conference") as Conferences
        setContentToolbar()
        setBodyDialogConference()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    private fun setContentToolbar() {
        toolbar_conference.navigationIcon = view?.context?.let { ContextCompat.getDrawable(it, R.drawable.ic_close) }
        toolbar_conference.setTitleTextColor(Color.WHITE)
        toolbar_conference.setNavigationOnClickListener {
            dismiss()
        }

        toolbar_conference.title = conferences.tag
    }

    private fun setBodyDialogConference() {
        view?.context?.let { img_conference_schedule_detail_dialog.setGlide(it, R.drawable.banner_platzi_conf_2) }
        tv_title_conference_schedule_detail_dialog.text = conferences.title
        val pattern = "dd/MM/yyyy hh:mm a"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val date = simpleDateFormat.format(conferences.dateTime)
        tv_conference_hour_schedule_detail_dialog.text = date
        tv_speaker_schedule_detail_dialog.text = conferences.speakers
        tv_tag_schedule_detail_dialog.text = conferences.tag
        tv_detail_conference_schedule_detail_dialog.text = conferences.description
    }
}