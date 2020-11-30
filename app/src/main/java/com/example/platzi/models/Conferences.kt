package com.example.platzi.models

import java.io.Serializable
import java.util.*

class Conferences : Serializable {
    lateinit var title : String
    lateinit var description : String
    lateinit var tag : String
    lateinit var dateTime : Date
    lateinit var speakers : String
}