package com.example.platzi.view.ui.fragments.scheduleFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.platzi.R
import com.example.platzi.models.Conferences
import com.example.platzi.view.adapters.SchedulesAdapter
import com.example.platzi.view.adapters.interfaces.ScheduleListener
import com.example.platzi.viewModel.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_schedule.*

class ScheduleFragment : Fragment(), ScheduleListener {
    private lateinit var scheduleAdapter : SchedulesAdapter
    private lateinit var viewModel : ScheduleViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ScheduleViewModel::class.java)
        viewModel.refresh()

        initAll()
    }

    private fun initAll() {
        scheduleAdapter = SchedulesAdapter(this)
        rv_schedule?.apply {
            layoutManager = LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
            adapter = scheduleAdapter
        }
        observerViewModel()
    }

    private fun observerViewModel(){
        viewModel.listSchedule.observe(viewLifecycleOwner, { schedule ->
            scheduleAdapter.updateDataConferences(schedule)
        })
        viewModel.isLoading.observe(viewLifecycleOwner,  {isLoading->
            if (isLoading != null){
                rl_container_progress_bar_schedule.visibility = View.INVISIBLE
            }
        })
    }

    override fun OnConferencesClick(conferences: Conferences, position: Int) {
        val bundle = bundleOf("conference" to conferences)
        findNavController().navigate(R.id.schedules_detail_fragment_dialog, bundle)
    }
}