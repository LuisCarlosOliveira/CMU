package com.example.mysmarthome.database.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mysmarthome.database.database.MySmartHomeDatabase
import com.example.mysmarthome.database.entities.PersonalConfigs
import com.example.mysmarthome.database.repositories.PersonalConfigsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonalConfigsViewModel(application: Application) : AndroidViewModel(application) {

    val repository : PersonalConfigsRepository
    val allPersonalConfigs : LiveData<List<PersonalConfigs>>


    init {
        val db = MySmartHomeDatabase.getDatabase(application)
        repository = PersonalConfigsRepository(db.getPersonalConfigsDao())
        allPersonalConfigs = repository.getPersonalConfigs()
    }

    fun getPersonalConfigs(): LiveData<List<PersonalConfigs>> {
        return repository.getPersonalConfigs()
    }

    fun insertPersonalConfigs(personalConfigs:PersonalConfigs){
        viewModelScope.launch(Dispatchers.IO){
            repository.insert(personalConfigs)
        }
    }

    fun updatePersonalConfigs(personalConfigs:PersonalConfigs){
        viewModelScope.launch(Dispatchers.IO){
            repository.update(personalConfigs)
        }
    }

    fun removePersonalConfigs(personalConfigs:PersonalConfigs){
        viewModelScope.launch(Dispatchers.IO){
            repository.delete(personalConfigs)
        }
    }

}