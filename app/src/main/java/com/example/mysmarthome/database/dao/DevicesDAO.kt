package com.example.mysmarthome.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mysmarthome.database.entities.Device

@Dao
interface DevicesDAO {

        @Query("select * from Device where connected = 1 ")
        fun getConnectedDevices(): LiveData<List<Device>>

        @Query("select * from Device where connected = 0 ")
        fun getUnconnectedDevices(): LiveData<List<Device>>

        @Query("select * from Device where device_Id = :device_id")
        fun getOneDevice(device_id:Int):LiveData<Device>

        @Update
        suspend fun update(device: Device)

}