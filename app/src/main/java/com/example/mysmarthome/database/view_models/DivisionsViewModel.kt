package com.example.mysmarthome.database.view_models

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mysmarthome.database.database.MySmartHomeDatabase
import com.example.mysmarthome.database.entities.Division
import com.example.mysmarthome.database.entities.relations.home_division.HomeWithDivisions
import com.example.mysmarthome.database.repositories.DivisionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class DivisionsViewModel(application: Application) : AndroidViewModel(application) {

    val repository: DivisionRepository
    val allDivisions: MutableLiveData<List<Division>>
    val division: MutableLiveData<Division>

    init {
        val db = MySmartHomeDatabase.getDatabase(application)
        repository = DivisionRepository(db.getDivisionDao())
        allDivisions = MutableLiveData<List<Division>>()
        division = MutableLiveData<Division>(null)
    }

    fun insertDivision(division: Division) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(division)
        }
    }

    fun getOneDivision(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("Divisao", division.value.toString())
            division.postValue(repository.getDivision(id))
            Log.d("Divisao", division.value.toString())
        }
    }
    fun getDivisions() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("Lista de divisões 1", "Obtendo lista de divisões")
            allDivisions.postValue( repository.getDivisions())
            Log.d("Lista de divisões 1", "Lista de divisões obtida: ${allDivisions.value}")
        }
    }

    fun getDivisionByHome(idHome: Int): LiveData<HomeWithDivisions> {
        return repository.getDivisionByHome(idHome)
    }

    fun updateDivision(division: Division) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(division)
        }
    }

    fun removeDivision(division: Division) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(division)
            getDivisions()
        }
    }

    fun getDivisions2(callback: (List<Division>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val divisions = repository.getDivisions()
            allDivisions.postValue(divisions)
            callback(divisions)
        }
    }

    fun removeAllDivisions(callback: (List<Division>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getDivisions { divisions ->
                divisions.forEach { division ->
                    runBlocking { repository.removeDivision(division) }
                }
                callback(divisions)
            }
        }
    }

}