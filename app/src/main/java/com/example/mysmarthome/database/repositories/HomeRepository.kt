package com.example.mysmarthome.database.repositories

import androidx.lifecycle.LiveData
import com.example.mysmarthome.database.dao.HomesDAO
import com.example.mysmarthome.database.entities.Home

class HomeRepository(val homeDao: HomesDAO) {

   /* fun getHomes(): LiveData<List<Home>> {
        return homeDao.getHomes()
    }*/

    fun getHome(id: Int): LiveData<Home> {
        return homeDao.getOneHome(id)
    }

    suspend fun insert(home: Home) {
        homeDao.insert(home)
    }

    suspend fun delete(home: Home) {
        homeDao.delete(home)
    }

}

