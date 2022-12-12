package com.example.mysmarthome.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mysmarthome.database.entities.Division

@Dao
interface DivisionsDAO {

        @Query("select * from Division")
        fun getDivisions(): LiveData<List<Division>>

        @Query("select * from Division where idDivision = :division_id")
        fun getOneDivision(division_id:Int):LiveData<Division>

       // @Query("select * from Division div, HomeWithDivisions hwd where div.idDivisionHome = :home_id and div.idDivisionHome = hwd.idHome")
        //fun getDivisionByHome(home_id: Int): LiveData<List<Division>>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(division:Division)

        @Update
        suspend fun update(division:Division)

        @Delete
        suspend fun delete(division:Division)

}