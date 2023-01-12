package com.example.mysmarthome.database.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mysmarthome.database.dao.UsersDAO
import com.example.mysmarthome.database.entities.Division
import com.example.mysmarthome.database.entities.Home
import com.example.mysmarthome.database.entities.User
import com.example.mysmarthome.database.entities.relations.home_user.HomeWithUsers
import com.example.mysmarthome.enums.TypeMember

class UserRepository(val userDao: UsersDAO) {

    fun getUsers(): LiveData<List<User>> {
        return userDao.getUsers()
    }

    suspend fun getOneUser(id: Int): User{
        return userDao.getOneUser(id)
    }

    suspend fun getUserByEmail(email: String): User {
        return userDao.getUserByEmail(email)
    }

    fun getUserByEmail2(email: String): LiveData<User> {
        return userDao.getUserByEmail2(email)
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

    fun getUsersByHome(idHome: Int): LiveData<HomeWithUsers>{
        return userDao.getUsersByHome(idHome)
    }

    fun getUsersByTypeMember(typeMember: String): LiveData<List<User>> {
        return userDao.getUsersByTypeMember(typeMember)
    }
}