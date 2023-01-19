package com.example.mysmarthome.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mysmarthome.database.entities.Division
import com.example.mysmarthome.database.entities.relations.home_division.HomeWithDivisions

@Dao
interface DivisionsDAO {


        @Query("select * from Division where idDivision = :idDivision")
        fun getOneDivision(idDivision:Int): Division

        @Query("select * from Division")
        fun getDivisions():List<Division>

        @Transaction
        @Query("select * from Home where idHome = :idHome ")
        fun getDivisionByHome(idHome: Int): LiveData<HomeWithDivisions>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(division:Division)

        @Update
        suspend fun update(division:Division)

        @Delete
        suspend fun delete(division:Division)

}