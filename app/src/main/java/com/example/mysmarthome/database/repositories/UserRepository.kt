package com.example.mysmarthome.database.repositories

import androidx.lifecycle.LiveData
import com.example.mysmarthome.database.dao.UsersDAO
import com.example.mysmarthome.database.entities.User

class UserRepository(val userDao: UsersDAO) {

    fun getUsers(): LiveData<List<User>> {
        return userDao.getUsers()
    }

    /*
        fun getUser(id: Int): LiveData<User>{
            return userDao.getOneUser(id)
        }
    */

    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun delete(user: User) {
        userDao.delete(user)
    }

}