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

        @Transaction
        @Query("select * from Division where idDivision = :idDivision")
        fun getConectedDevicesByDivision(idDivision: Int): LiveData<DivisionWithDevices>

        @Query("select * from Device where type = :typeDevice")
        fun getDevicesByTypeDevice(typeDevice:String): LiveData<List<Device>>

        @Query("select * from Device where porta = :port")
        fun getDeviceByPort(port: Int): LiveData<Device>

}
