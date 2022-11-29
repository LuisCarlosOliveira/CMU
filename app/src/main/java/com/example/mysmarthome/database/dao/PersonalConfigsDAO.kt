package com.example.mysmarthome.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mysmarthome.database.entities.PersonalConfigs

@Dao
interface PersonalConfigsDAO {

    @Query("select * from PersonalConfigs")
    fun getPersonalConfigs(): LiveData<List<PersonalConfigs>>

    /*
    @Query("select * from PersonalConfigs,Device,User where personalConfigs_Id = :personalConfigs_id")
    fun getPersonalConfigByDevice(personalConfigs_id:Int):LiveData<PersonalConfigs>
    */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(personalConfigs: PersonalConfigs)

    @Update
    suspend fun update(personalConfigs: PersonalConfigs)

    @Delete
    suspend fun delete(personalConfigs: PersonalConfigs)

}