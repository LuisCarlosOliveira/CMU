package com.example.mysmarthome.database.view_models

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysmarthome.database.entities.Device
import com.example.mysmarthome.database.entities.Division
import com.example.mysmarthome.database.entities.Home
import com.example.mysmarthome.database.repositories.DeviceRepository
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FirestoreViewModel : ViewModel()  {

    val dbF : FirebaseFirestore

    init {
        dbF = Firebase.firestore
    }

    fun insertHomeFirestore(tempHome: Home) {
        viewModelScope.launch(Dispatchers.IO) {
             val homeRef = dbF.collection("homes").document(tempHome.idF)
            homeRef.set(tempHome)
                .addOnSuccessListener { Log.d(ContentValues.TAG, "Home added with ID: ${homeRef.id}") }
                .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error adding home", e) }
        }
    }

    fun insertDivisionFirestore(division: Division, homeIdF: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val homeRef = dbF.collection("homes").document(homeIdF)
            Log.d(ContentValues.TAG, "Home: ${homeRef.id}")
            val divisionRef = homeRef.collection("divisions").document(division.idF)
            divisionRef.set(division)
                .addOnSuccessListener {
                    Log.d(
                        ContentValues.TAG,
                        "Division added with ID: ${divisionRef.id}"
                    )
                }
                .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error adding division", e) }
        }
    }

    fun insertDeviceFirestore(device: Device,homeIdF: String ,divisionIdF : String) {
        viewModelScope.launch(Dispatchers.IO) {
            val homeRef = dbF.collection("homes").document(homeIdF)
            val divisionRef = homeRef.collection("divisions").document(divisionIdF)
            val deviceRef = divisionRef.collection("devices").document()
            deviceRef.set(device)
                .addOnSuccessListener { Log.d(ContentValues.TAG, "Device added with ID: ${deviceRef.id}") }
                .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error adding device", e) }

        }
    }
}