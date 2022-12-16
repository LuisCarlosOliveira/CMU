package com.example.mysmarthome.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mysmarthome.database.entities.Home
import com.example.mysmarthome.database.entities.User
import com.example.mysmarthome.enums.TypeMember

@Dao
interface UsersDAO {

    @Query("select * from User")
    fun getUsers(): LiveData<List<User>>

    @Query("select * from User where idUser = :user_id")
    fun getOneUser(user_id: Int):LiveData<User>

    @Query("select * from User where typeMember = :typeMember")
    fun getUsersByTypeMember(typeMember: TypeMember): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    //pedidos de adesao

}