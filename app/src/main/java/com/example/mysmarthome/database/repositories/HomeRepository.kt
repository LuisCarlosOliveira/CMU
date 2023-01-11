package com.example.mysmarthome.database.repositories

import androidx.lifecycle.LiveData
import com.example.mysmarthome.database.dao.HomesDAO
import com.example.mysmarthome.database.entities.Home

class HomeRepository(val homeDao: HomesDAO) {

    fun getHomes(): LiveData<List<Home>> {
        return homeDao.getHomes()
    }
    fun getHome(home_id: Int): Home {
        return homeDao.getOneHome(home_id)
    }

    suspend fun insert(home: Home) :Int{
        val _id = homeDao.insert(home)
        return _id.toInt()
    }

    suspend fun delete(home: Home) {
        homeDao.delete(home)
    }

    fun getHomeByUser(user_id: Int): Home{
        return homeDao.getHomeByUser(user_id)
    }

}

