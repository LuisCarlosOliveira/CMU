package com.example.mysmarthome.database.view_models

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mysmarthome.database.database.MySmartHomeDatabase
import com.example.mysmarthome.database.entities.Home
import com.example.mysmarthome.database.entities.User
import com.example.mysmarthome.database.repositories.HomeRepository
import com.example.mysmarthome.database.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomesViewModel(application: Application) : AndroidViewModel(application) {

    val repository: HomeRepository
    val userRepo: UserRepository
    val divisionViewModal: DivisionsViewModel
    val home: MutableLiveData<Home>

    init {
        val db = MySmartHomeDatabase.getDatabase(application)
        repository = HomeRepository(db.getHomeDao())
        userRepo = UserRepository(db.getUsersDao())
        divisionViewModal = DivisionsViewModel(application)
        home = MutableLiveData<Home>(null)
    }

    fun insertHome(tempHome: Home) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(tempHome)
            home.postValue(tempHome)
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
                Log.d("Alerta", "Ainda não tem casa criada")
            }
        }
    }

    fun getHomes(): List<Home> {
        return repository.getHomes()
    }

}
