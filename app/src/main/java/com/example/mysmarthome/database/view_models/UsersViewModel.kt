package com.example.mysmarthome.database.view_models

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mysmarthome.database.database.MySmartHomeDatabase
import com.example.mysmarthome.database.entities.Home
import com.example.mysmarthome.database.entities.User
import com.example.mysmarthome.database.entities.relations.home_user.HomeWithUsers
import com.example.mysmarthome.database.repositories.UserRepository
import com.example.mysmarthome.enums.TypeMember
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import okhttp3.internal.wait

class UsersViewModel(application: Application) : AndroidViewModel(application) {

    val repository: UserRepository
    val allUsers: LiveData<List<User>>
    val user: MutableLiveData<User>
    val authState: MutableLiveData<AuthStatus>
    val fAuth: FirebaseAuth


    init {
        val db = MySmartHomeDatabase.getDatabase(application)
        repository = UserRepository(db.getUsersDao())
        allUsers = repository.getUsers()
        user = MutableLiveData<User>(null)
        authState = MutableLiveData(AuthStatus.NOLOGGIN)
        fAuth = Firebase.auth

    }

    fun getUsers(): LiveData<List<User>> {
        return repository.getUsers()
    }

    fun insertUser(currentUser: User): String {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(currentUser)
            user.postValue(currentUser)
        }
        return currentUser.email
    }

    fun updateUser(currentUser: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(currentUser)
            user.postValue(currentUser)
        }
    }

    fun removeUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(user)
        }
    }

    suspend fun getOneUser(id: Int): User {
        return repository.getOneUser(id)
    }

    fun getUserByEmail2(email: String): LiveData<User> {
        return repository.getUserByEmail2(email)
    }


    fun getUserByEmail(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                var currentUser = repository.getUserByEmail(email)
                user.postValue(currentUser)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getUsersByHome(idHome: Int): LiveData<HomeWithUsers> {
        return repository.getUsersByHome(idHome)
    }

    fun getUsersByTypeMember(typeMember: String): LiveData<List<User>> {
        return repository.getUsersByTypeMember(typeMember)
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            try {
                val result = fAuth.createUserWithEmailAndPassword(email, password).await()
                if (result != null && result.user != null) {
                    authState.postValue(AuthStatus.LOGGED)
                    Log.d("Register", "logged in")
                    return@launch
                }
                Log.d("Register", "anonymous")
                authState.postValue(AuthStatus.NOLOGGIN)
                return@launch
            } catch (e: Exception) {
            }
        }
    }

    fun updatePass(email: String, password: String) {
        viewModelScope.launch {
            fAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "Email sent.")
                        user.value!!.password = password
                        updateUser(user.value!!)
                        // Show success message to the user
                    } else {
                        Log.d(TAG, "Failed to send reset email.")
                        // Show error message to the user
                    }
                }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val result = fAuth.signInWithEmailAndPassword(email, password).await()
                if (result != null && result.user != null) {
                    getUserByEmail(email)
                    authState.postValue(AuthStatus.LOGGED)
                    Log.d("Login", "logged in")
                    return@launch
                }
                Log.d("Login", "anonymous")
                authState.postValue(AuthStatus.NOLOGGIN)
                return@launch
            } catch (e: Exception) {
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            fAuth.signOut()
            authState.postValue(AuthStatus.NOLOGGIN)
            Log.d("Login", "logout")
        }
    }

    enum class AuthStatus {
        LOGGED, NOLOGGIN
    }


}

