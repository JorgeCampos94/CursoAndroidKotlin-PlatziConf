package com.example.platzi.view.adapters.interfaces

import com.example.platzi.models.Speakers

interface SpeakerListener {
    fun OnSpeakerClick(speakers : Speakers, position : Int)
}