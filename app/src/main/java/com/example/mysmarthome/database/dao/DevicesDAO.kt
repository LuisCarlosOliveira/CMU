package com.example.mysmarthome.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mysmarthome.database.entities.Device
import com.example.mysmarthome.database.entities.relations.device_division.*
import com.example.mysmarthome.enums.TypeDevice

@Dao
interface DevicesDAO {

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert(device: Device)

        @Update
        suspend fun update(device: Device?)

        @Query("select * from Device where idDevice = :idDevice")
        fun getOneDevice(idDevice:Int): LiveData<Device>

        @Query("select * from Device")
        fun getDevices(): LiveData<List<Device>>

        @Query("select * from Device where idDivisionDevice != 0")
        fun getConnectedDevices(): LiveData<List<Device>>

        @Query("select * from Device where idDivisionDevice = 0")
        fun getUnconnectedDevices(): LiveData<List<Device>>

        @Query("select * from Device where idDivisionDevice = :idDivision")
        fun getConnectedDevicesByDivision(idDivision: Int): LiveData<List<Device>>

        @Query("select * from Device where type = :typeDevice")
        fun getDevicesByTypeDevice(typeDevice:String): LiveData<List<Device>>
}
