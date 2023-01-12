package com.example.mysmarthome.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mysmarthome.database.entities.Home
import com.example.mysmarthome.database.entities.relations.device_division.DivisionWithDevices

@Dao
interface
HomesDAO {

    @Query("select * from Home")
    fun getHomes(): List<Home>

    @Query("select * from Home where idHome = :home_id")
    fun getOneHome(home_id: Int): Home

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(home: Home) : Long

    @Delete
    suspend fun delete(home: Home)

    //por enquanto esta 1 casa por user
    @Transaction
    @Query("select * from Home where idHome in (select idUserHome from User where idUser = :user_id)")
    fun getHomeByUser(user_id:Int): Home

}