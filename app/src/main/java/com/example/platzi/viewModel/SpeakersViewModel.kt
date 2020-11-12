package com.example.platzi.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.platzi.models.Speakers
import com.example.platzi.network.FireStoreServices
import com.example.platzi.network.interfaces.Callback

class SpeakersViewModel  : ViewModel() {
    val fireStoreServices = FireStoreServices()
    var listSpeakers: MutableLiveData<List<Speakers>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getScheduleFromFirebase()
    }

    fun getScheduleFromFirebase(){
        fireStoreServices.getSpeakers(object  : Callback<List<Speakers>> {
            override fun OnSuccess(result: List<Speakers>?) {
                listSpeakers.postValue(result)
                processFinished()
            }

            override fun OnFailed(exception: Exception) {
                processFinished()
            }
        })
    }

    private fun processFinished() {
        isLoading.value = true
    }
}