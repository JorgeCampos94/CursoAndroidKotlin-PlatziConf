package com.example.platzi.view.ui.fragments.speakersFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.platzi.R
import com.example.platzi.models.Speakers
import com.example.platzi.utility.setVisibleOrGone
import com.example.platzi.view.adapters.SpeakersAdapter
import com.example.platzi.view.adapters.interfaces.SpeakerListener
import com.example.platzi.viewModel.SpeakersViewModel
import kotlinx.android.synthetic.main.fragment_speakers.*

class SpeakersFragment : Fragment(), SpeakerListener {
    private lateinit var speakerAdapter : SpeakersAdapter
    private lateinit var viewModel : SpeakersViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speakers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(SpeakersViewModel::class.java)
        viewModel.refresh()

        initAll()
    }

    private fun initAll() {
        context?.let { mContext ->
            speakerAdapter = SpeakersAdapter(mContext,this)
        }
        rv_speakers.apply {
            layoutManager = GridLayoutManager(view?.context, 2)
            adapter = speakerAdapter
        }
        observerSpeaker()
    }

    private fun observerSpeaker() {
        viewModel.listSpeakers.observe(viewLifecycleOwner, {speakerUpdate ->
            speakerAdapter.updateDataSpeakers(speakerUpdate)
        })

        viewModel.isLoading.observe(viewLifecycleOwner, {isLoading ->
            if (isLoading != null){
                rl_container_progress_bar_speakers.visibility = View.INVISIBLE
            }
        })
    }

    override fun OnSpeakerClick(speakers: Speakers, position: Int) {
        val bundle = bundleOf("speaker" to speakers)
        findNavController().navigate(R.id.speakers_detail_fragment_dialog, bundle)
    }
}