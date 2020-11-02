package com.example.platzi.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.platzi.R
import com.example.platzi.models.Conferences
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ScheduleAdapter (private val mContext: Context) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>(){

    private val listConference = ArrayList<Conferences>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent))

    override fun getItemCount() = listConference.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val conference = listConference[position]
        holder.bind(conference, mContext)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val tvConferenceHour: TextView = itemView.findViewById(R.id.tv_time_conference_item_schedule)
        val tvConferenceAmPm: TextView = itemView.findViewById(R.id.tv_day_conference_item_schedule)
        val tvConferenceName : TextView = itemView.findViewById(R.id.tv_title_conference_item_schedule)
        val tvConferenceSpeaker : TextView = itemView.findViewById(R.id.tv_name_expositor_item_schedule)
        val tvConferenceTag : TextView = itemView.findViewById(R.id.tv_tag_expositor_item_schedule)

        fun bind(itemView: Conferences, context: Context){
            tvConferenceName.text = itemView.title
            tvConferenceSpeaker.text = itemView.speakers
            tvConferenceTag.text = itemView.tag

            val simpleDateFormat = SimpleDateFormat("HH:mm")
            val simpleDateFormatAmPm = SimpleDateFormat("a")

            val cal = Calendar.getInstance()
            cal.time = itemView.dateTime
            val hourFormat = simpleDateFormat.format(itemView.dateTime)

            tvConferenceHour.text = hourFormat
        }
    }
}