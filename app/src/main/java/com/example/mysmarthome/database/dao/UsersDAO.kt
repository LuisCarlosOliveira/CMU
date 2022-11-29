package com.example.mysmarthome.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mysmarthome.database.entities.User

@Dao
interface UsersDAO {

    @Query("select * from User")
    fun getUsers(): LiveData<List<User>>

    /*
    @Query("select * from User where user_Id = :user_id")
    fun getOneUser(user_id: Int):LiveData<User>
    */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

}