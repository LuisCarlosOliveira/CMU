package com.example.mysmarthome.database.view_models

import android.app.Application
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mysmarthome.database.database.MySmartHomeDatabase
import com.example.mysmarthome.database.entities.Address
import com.example.mysmarthome.database.entities.Division
import com.example.mysmarthome.database.entities.Home
import com.example.mysmarthome.database.entities.User
import com.example.mysmarthome.database.repositories.DivisionRepository
import com.example.mysmarthome.database.repositories.HomeRepository
import com.example.mysmarthome.database.repositories.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomesViewModel(application: Application) : AndroidViewModel(application) {

    val repository: HomeRepository
    val userRepo: UserRepository
    val divisionViewModal: DivisionsViewModel
    val home: MutableLiveData<Home>
    val dbF : FirebaseFirestore

    init {
        val db = MySmartHomeDatabase.getDatabase(application)
        repository = HomeRepository(db.getHomeDao())
        userRepo = UserRepository(db.getUsersDao())
        divisionViewModal = DivisionsViewModel(application)
        home = MutableLiveData<Home>(null)
        dbF = Firebase.firestore
    }

    fun insertHome(tempHome: Home) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(tempHome)
            home.postValue(tempHome)
            val homeRef = dbF.collection("homes").document(tempHome.idF)
            homeRef.set(tempHome)
                .addOnSuccessListener { Log.d(ContentValues.TAG, "Home added with ID: ${homeRef.id}") }
                .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error adding home", e) }

        }
    }

    fun removeHome() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.delete(home.value!!)
                Log.d("Eliminar Casa", "apagar casa")
                divisionViewModal.removeAllDivisions{divisionViewModal.allDivisions }
                Log.d("Acabou", "apagar casa")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("aiaiai", "apagar casa")
            }
        }
    }

    fun getOneHomeF(home_id: Int):Home {
        var home: Home? =null
        viewModelScope.launch(Dispatchers.IO) {
            home= repository.getHome(home_id)
        }
        return home!!
    }

    fun getOneHome(home_id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                var currentHome = repository.getHome(home_id)
                home.postValue(currentHome)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getHomeByUser(user_id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                var currentHome = repository.getHomeByUser(user_id)
                home.postValue(currentHome)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getFirstHome(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                var currentHome = getHomes()
                home.postValue(currentHome.get(0))
            } catch (e: Exception) {
                Log.d("Alerta", "Ainda nÃ£o tem casa criada")
            }
        }
    }

    fun getHomes(): List<Home> {
        return repository.getHomes()
    }

    fun getAllFiestore(idF: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val homesRef = dbF.collection("homes").whereEqualTo("idF", idF)
            homesRef.get().addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                    val addressMap = document.data["address"] as Map<String, String>
                    val address = Address(
                        street = addressMap["street"] ?: "",
                        postalCode = addressMap["postalCode"] ?: "",
                        city = addressMap["city"] ?: "",
                        country = addressMap["country"] ?: ""
                    )
                    val home = Home(idF = document.data["idF"] as String, name = document.data["name"] as String, address = address)
                    insertHome(home)
                    Log.d("CASAAAAAAAAAAAAAAAA", home.toString())
                }
            }
                .addOnFailureListener { exception ->
                    Log.w(ContentValues.TAG, "Error getting homes", exception)
                }


        }
    }
}
