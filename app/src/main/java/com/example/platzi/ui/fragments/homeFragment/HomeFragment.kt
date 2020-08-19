package com.example.platzi.ui.fragments.homeFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.platzi.R
import com.example.platzi.adapters.CardsHomeAdapter
import com.example.platzi.models.CardDescriptionHome
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private var recyclerViewCardHome : RecyclerView? = null
    private var adapterCardHome : CardsHomeAdapter? = null
    private var listCardHome : MutableList<CardDescriptionHome>? = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val viewCardHome = inflater.inflate(R.layout.fragment_home, container, false)
        context?.let {
            initAll(viewCardHome)
        }
        return viewCardHome
    }

    private fun initAll(viewCardHome: View?) {
        context?.let {
            getDataCardList()
            recyclerViewCardHome = viewCardHome?.recyclerView_cards_description_event_home
            adapterCardHome = CardsHomeAdapter(it)
            Log.w("GGGG","ENTRO")
            listCardHome?.let{listCardHome->
                adapterCardHome?.setBrandsList(listCardHome)
            }
            recyclerViewCardHome?.layoutManager = LinearLayoutManager(it, LinearLayoutManager.VERTICAL , false)
            recyclerViewCardHome?.adapter = adapterCardHome
        }
    }

    private fun getDataCardList() {
        listCardHome?.clear()
        listCardHome?.add(
            CardDescriptionHome(
                R.drawable.banner_platzi_conf_1,
                getString(R.string.label_question_whats_is_it_platziConf),
                getString(R.string.label_response_whats_is_it_platziConf)
            )
        )

        listCardHome?.add(
            CardDescriptionHome(
                R.drawable.banner_platzi_conf_2,
                getString(R.string.label_question_why_attend),
                getString(R.string.label_response_why_attend)
            )
        )

        listCardHome?.add(
            CardDescriptionHome(
                R.drawable.banner_platzi_conf_3,
                getString(R.string.label_title_information_platziConf),
                getString(R.string.label_body_information_platziConf)
            )
        )
    }
}