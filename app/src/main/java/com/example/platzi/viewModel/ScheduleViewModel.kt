package com.example.platzi.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.platzi.models.Conferences
import com.example.platzi.network.FireStoreServices
import com.example.platzi.network.interfaces.Callback

class ScheduleViewModel : ViewModel() {
    val fireStoreServices = FireStoreServices()
    var listSchedule : MutableLiveData<List<Conferences>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getScheduleFromFirebase()
    }

    fun getScheduleFromFirebase(){
        fireStoreServices.getSchedule(object  : Callback<List<Conferences>> {
            override fun OnSuccess(result: List<Conferences>?) {
                listSchedule.postValue(result)
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

