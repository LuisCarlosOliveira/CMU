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
    val home: MutableLiveData<Home>
    init {
        val db = MySmartHomeDatabase.getDatabase(application)
        repository = HomeRepository(db.getHomeDao())
        userRepo=UserRepository(db.getUsersDao())
        home = MutableLiveData<Home>(null)

    }

    fun insertHome(tempHome: Home) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(tempHome)
            home.postValue(tempHome)
        }

    }

    fun removeHome(home: Home) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(home)
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
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }


    fun getHomes(): LiveData<List<Home>> {
        return repository.getHomes()
    }

}
