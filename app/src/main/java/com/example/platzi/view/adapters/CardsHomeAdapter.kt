package com.example.platzi.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.platzi.R
import com.example.platzi.models.CardDescriptionHome
import com.example.platzi.utility.setGlide
import kotlinx.android.synthetic.main.item_card_detail_platzi_conf_home.view.*

class CardsHomeAdapter(private val mContext: Context) : RecyclerView.Adapter<CardsHomeAdapter.CardsHomeDescriptionEvent> () {

    private var eventDescriptionList : MutableList<CardDescriptionHome> = ArrayList()

    override fun onCreateViewHolder(parentView: ViewGroup, viewType: Int): CardsHomeDescriptionEvent {
        val layoutInflater = LayoutInflater.from(parentView.context)
        return CardsHomeDescriptionEvent(layoutInflater.inflate(R.layout.item_card_detail_platzi_conf_home, parentView, false))
    }

    override fun getItemCount(): Int {
        return eventDescriptionList.size
    }

    override fun onBindViewHolder(holder: CardsHomeDescriptionEvent, position: Int) {
        val item = eventDescriptionList[position]
        holder.bind(item, mContext)
    }

    fun setBrandsList(listBrands: MutableList<CardDescriptionHome>) {
        eventDescriptionList.clear()
        eventDescriptionList.addAll(listBrands)
        notifyDataSetChanged()
    }

    class CardsHomeDescriptionEvent(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgCard = itemView.iv_banner_card_home
        private val titleCard = itemView.tv_title_card_home
        private val subtitleCard = itemView.tv_subtitle_card_home

        fun bind(cardDescription: CardDescriptionHome, context: Context){
            titleCard.text = cardDescription.titleDescription as String
            subtitleCard.text = cardDescription.bodyDescription as String
            imgCard.setGlide(context ,cardDescription.imgDescription)
        }
    }
}