package com.example.platzi.network

import com.example.platzi.interfaces.Callback
import com.example.platzi.models.Conferences
import com.example.platzi.models.Speakers
import com.example.platzi.utilitys.Constants
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

class FireStoreServices{
    //Instancia a una conexion directa con la base de datos
    val firebaseFireStore = FirebaseFirestore.getInstance()
    //Obtener nuestros datos y tenerlos o descargarlos  de manera ofLine(modo sin conexion) tener persistencia de datos
    val settingsFireStore = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()
    //Se va a ejecutar cuando se inicie la clase para tener los datos de manera ofLine
    init {
        firebaseFireStore.firestoreSettings = settingsFireStore
    }

    fun getSpeakers(callback : Callback<List<Speakers>>){
        firebaseFireStore.collection(Constants.CONST_COLLECTION_FIRESTORE_SPEAKERS)
            .orderBy("category")
            .get()
            .addOnSuccessListener { result ->
                for (doc in result){
                    val list = result.toObjects(Speakers::class.java)
                    callback.OnSuccess(list)
                    break
                }
            }
    }

    fun getSchedule(callback : Callback<List<Conferences>>){
        firebaseFireStore.collection(Constants.CONST_COLLECTION_FIRESTORE_CONFERENCES)
            .get()
            .addOnSuccessListener { result ->
                for (doc in result){
                    val list = result.toObjects(Conferences::class.java)
                    callback.OnSuccess(list)
                    break
                }
            }
    }
}