package com.example.mysmarthome.database.repositories

import androidx.lifecycle.LiveData
import com.example.mysmarthome.database.dao.UsersDAO
import com.example.mysmarthome.database.entities.Division
import com.example.mysmarthome.database.entities.Home
import com.example.mysmarthome.database.entities.User
import com.example.mysmarthome.enums.TypeMember

class UserRepository(val userDao: UsersDAO) {

    fun getUsers(): LiveData<List<User>> {
        return userDao.getUsers()
    }

    fun getOneUser(id: Int): LiveData<User>{
        return userDao.getOneUser(id)
    }

  /*  fun getHomeByUser(id: Int): LiveData<Home>{
        return userDao.getHomeByUser(id)
    }

    fun getHomesByUser(id: Int): LiveData<List<Home>> {
        return userDao.getHomesByUser(id)
    }*/

    fun getUsersByTypeMember(typeMember: TypeMember): LiveData<List<User>> {
        return userDao.getUsersByTypeMember(typeMember)
    }

    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun update(user: User) {
        userDao.update(user)
    }

    suspend fun delete(user: User) {
        userDao.delete(user)
    }

}