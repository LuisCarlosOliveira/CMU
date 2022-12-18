package com.example.mysmarthome.database.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mysmarthome.database.database.MySmartHomeDatabase
import com.example.mysmarthome.database.entities.Home
import com.example.mysmarthome.database.entities.User
import com.example.mysmarthome.database.entities.relations.home_user.HomeWithUsers
import com.example.mysmarthome.database.repositories.UserRepository
import com.example.mysmarthome.enums.TypeMember
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsersViewModel(application: Application) : AndroidViewModel(application) {

    val repository: UserRepository
    val allUsers: LiveData<List<User>>

    init {
        val db = MySmartHomeDatabase.getDatabase(application)
        repository = UserRepository(db.getUsersDao())
        allUsers = repository.getUsers()
    }

    fun getUsers(): LiveData<List<User>> {
        return repository.getUsers()
    }

    fun insertUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(user)
        }
    }

    fun removeUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(user)
        }
    }

    fun getOneUser(id: Int): LiveData<User> {
        return repository.getOneUser(id)
    }

    fun getUserByEmail(email: String): LiveData<User> {
        return repository.getUserByEmail(email)
    }

    fun getUsersByHome(idHome: Int): LiveData<HomeWithUsers>{
        return repository.getUsersByHome(idHome)
    }
    fun getUsersByTypeMember(typeMember: String): LiveData<List<User>> {
        return repository.getUsersByTypeMember(typeMember)
    }
}