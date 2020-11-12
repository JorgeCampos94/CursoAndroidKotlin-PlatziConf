package com.example.platzi.view.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.platzi.R
import com.example.platzi.models.Conferences
import com.example.platzi.view.adapters.interfaces.ScheduleListener
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SchedulesAdapter (val scheduleListener : ScheduleListener) : RecyclerView.Adapter<SchedulesAdapter.ViewHolder>(){

    private val listConference = ArrayList<Conferences>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false))

    override fun getItemCount() = listConference.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val conferenceItem = listConference[position]
        holder.bind(conferenceItem, position , scheduleListener)
    }

    fun updateDataConferences(data: List<Conferences>){
        listConference.clear()
        listConference.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private val tvConferenceHour: TextView = itemView.findViewById(R.id.tv_time_conference_item_schedule)
        private val tvConferenceAmPm: TextView = itemView.findViewById(R.id.tv_day_conference_item_schedule)
        private val tvConferenceName : TextView = itemView.findViewById(R.id.tv_title_conference_item_schedule)
        private val tvConferenceSpeaker : TextView = itemView.findViewById(R.id.tv_name_expositor_item_schedule)
        private val tvConferenceTag : TextView = itemView.findViewById(R.id.tv_tag_expositor_item_schedule)

        @SuppressLint("SimpleDateFormat")
        fun bind(conferencesItem: Conferences, position: Int, scheduleListener: ScheduleListener){
            tvConferenceName.text = conferencesItem.title
            tvConferenceSpeaker.text = conferencesItem.speakers
            tvConferenceTag.text = conferencesItem.tag

            val simpleDateFormat = SimpleDateFormat("HH:mm")
            val simpleDateFormatAmPm = SimpleDateFormat("a")

            val cal = Calendar.getInstance()
            cal.time = conferencesItem.dateTime
            val hourFormat = simpleDateFormat.format(conferencesItem.dateTime)

            tvConferenceHour.text = hourFormat
            tvConferenceAmPm.text = simpleDateFormatAmPm.format(conferencesItem.dateTime).toUpperCase(Locale.ROOT)

            itemView.setOnClickListener {
                scheduleListener.OnConferencesClick(conferencesItem, position)
            }
        }
    }
}