package com.example.mysmarthome.database.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.mysmarthome.database.dao.DivisionsDAO
import com.example.mysmarthome.database.entities.Division
import com.example.mysmarthome.database.entities.relations.home_division.HomeWithDivisions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DivisionRepository(val divisionDao: DivisionsDAO) {

    suspend fun getDivisions(): List<Division> {
        val divisions =  divisionDao.getDivisions()
        Log.d("DivisionsRepository", "Divisions: $divisions")
        return divisions
    }

    suspend fun getDivision(id: Int): Division {
        return divisionDao.getOneDivision(id)
    }

    fun getDivisionByHome(idHome: Int): LiveData<HomeWithDivisions>{
        return divisionDao.getDivisionByHome(idHome)
    }

    suspend fun insert(division: Division) {
        divisionDao.insert(division)
    }

    suspend fun update(division: Division) {
        divisionDao.update(division)
    }

    suspend fun delete(division: Division) {
        divisionDao.delete(division)
    }

    fun getDivisions(callback: (List<Division>) -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            callback(divisionDao.getDivisions())
        }
    }

    fun removeDivision(division: Division) {
        GlobalScope.launch(Dispatchers.IO) {
            divisionDao.delete(division)
        }
    }
}