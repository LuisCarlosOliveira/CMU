package com.example.mysmarthome.database.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mysmarthome.database.database.MySmartHomeDatabase
import com.example.mysmarthome.database.entities.Division
import com.example.mysmarthome.database.repositories.DivisionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DivisionsViewModel(application: Application) : AndroidViewModel(application) {

    val repository: DivisionRepository
    val allDivisions: LiveData<List<Division>>

    init {
        val db = MySmartHomeDatabase.getDatabase(application)
        repository = DivisionRepository(db.getDivisionDao())
        allDivisions = repository.getDivisions()
    }

    fun getDivisions(): LiveData<List<Division>> {
        return repository.getDivisions()
    }

    fun insertDivision(division: Division) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(division)
        }
    }

    fun updateDivision(division: Division) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(division)
        }
    }

    fun removeDivision(division: Division) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(division)
        }
    }

    fun getOneDivision(id: Int): LiveData<Division> {
        return repository.getDivision(id)
    }

   /* fun getDivisionByHome(id: Int): LiveData<List<Division>> {
        return repository.getDivisionByHome(id)
    }
*/


}