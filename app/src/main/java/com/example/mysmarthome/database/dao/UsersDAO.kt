package com.example.mysmarthome.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mysmarthome.database.entities.Home
import com.example.mysmarthome.database.entities.User
import com.example.mysmarthome.database.entities.relations.home_user.HomeWithUsers
import com.example.mysmarthome.enums.TypeMember

@Dao
interface UsersDAO {


    @Query("select * from User")
    fun getUsers(): LiveData<List<User>>

    @Query("select * from User where idUser = :user_id")
    fun getOneUser(user_id: Int):LiveData<User>

    @Query("select * from User where email = :email")
    fun getUserByEmail(email: String): LiveData<User>

    @Query("select * from User where typeMember = :type")
    fun getUsersByTypeMember(type: String): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Transaction
    @Query("select * from Home where idHome = :idHome ")
    fun getUsersByHome(idHome: Int): LiveData<HomeWithUsers>

    //pedidos de adesao

}