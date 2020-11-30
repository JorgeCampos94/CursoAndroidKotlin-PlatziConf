package com.example.platzi.view.adapters.interfaces

import com.example.platzi.models.Conferences

interface ScheduleListener {
    fun OnConferencesClick(conferences: Conferences, position : Int)
}