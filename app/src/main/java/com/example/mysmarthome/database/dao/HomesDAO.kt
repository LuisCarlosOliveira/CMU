package com.example.mysmarthome.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mysmarthome.database.entities.Home

@Dao
interface HomesDAO {

    @Query("select * from Home")
    fun getHomes(): LiveData<List<Home>>

    @Query("select * from Home where idHome = :home_id")
    fun getOneHome(home_id: Int): LiveData<Home>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(home: Home)

    @Delete
    suspend fun delete(home: Home)

}