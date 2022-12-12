package com.example.mysmarthome.database.repositories

import androidx.lifecycle.LiveData
import com.example.mysmarthome.database.dao.DivisionsDAO
import com.example.mysmarthome.database.entities.Division

class DivisionRepository(val divisionDao: DivisionsDAO) {

    fun getDivisions(): LiveData<List<Division>> {
        return divisionDao.getDivisions()
    }

    fun getDivision(id: Int): LiveData<Division> {
        return divisionDao.getOneDivision(id)
    }

  /*  fun getDivisionByHome(id: Int): LiveData<List<Division>> {
        return divisionDao.getDivisionByHome(id)
    }*/

    suspend fun insert(division: Division) {
        divisionDao.insert(division)
    }

    suspend fun update(division: Division) {
        divisionDao.update(division)
    }

    suspend fun delete(division: Division) {
        divisionDao.delete(division)
    }

}