package com.example.platzi.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.platzi.R
import com.example.platzi.models.Speakers
import com.example.platzi.utility.setGlide
import com.example.platzi.view.adapters.interfaces.SpeakerListener

class SpeakersAdapter(private val mContext : Context, val speakerListener : SpeakerListener) : RecyclerView.Adapter<SpeakersAdapter.ViewHolder>() {

    private val listSpeakers = ArrayList<Speakers>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_card_speakers, parent, false))

    override fun getItemCount() = listSpeakers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val speakerItem = listSpeakers[position]
        holder.bind(speakerItem, mContext, position, speakerListener)
    }

    fun updateDataSpeakers(dataListSpeakers : List<Speakers>){
        listSpeakers.clear()
        listSpeakers.addAll(dataListSpeakers)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        private val imgSpeakerProfile : ImageView = itemView.findViewById(R.id.iv_profile_item_speaker)
        private val tvSpeakerName : TextView = itemView.findViewById(R.id.tv_name_item_speaker)
        private val tvSpeakerDescriptionJob : TextView = itemView.findViewById(R.id.tv_job_description_item_speaker)

        fun bind(speakerItem : Speakers , mContext : Context, position : Int , speakerListener : SpeakerListener){

            imgSpeakerProfile.setGlide(mContext,speakerItem.image.toString(), R.mipmap.ic_launcher)
            tvSpeakerName.text = speakerItem.name
            tvSpeakerDescriptionJob.text = speakerItem.workPlace

            itemView.setOnClickListener {
                speakerListener.OnSpeakerClick(speakerItem, position)
            }
        }
    }
}