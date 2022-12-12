package com.example.mysmarthome.database.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mysmarthome.database.database.MySmartHomeDatabase
import com.example.mysmarthome.database.entities.Home
import com.example.mysmarthome.database.repositories.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
/*
class HomesViewModel(application: Application) : AndroidViewModel(application) {

    val repository: HomeRepository
    val allHomes: LiveData<List<Home>>

   init {
        val db = MySmartHomeDatabase.getDatabase(application)
        repository = HomeRepository(db.getHomeDao())
        allHomes = repository.getHomes()
    }

    fun getHomes(): LiveData<List<Home>> {
        return repository.getHomes()
    }

    fun insertHome(home: Home) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(home)
        }
    }

    fun removeHome(home: Home) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(home)
        }
    }

    fun getOneHome(id: Int): LiveData<Home> {
        return repository.getHome(id)
    }

}*/
