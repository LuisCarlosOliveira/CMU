package com.example.mysmarthome.database.repositories

import androidx.lifecycle.LiveData
import com.example.mysmarthome.database.dao.DevicesDAO
import com.example.mysmarthome.database.entities.Device

class DeviceRepository(val deviceDao: DevicesDAO) {

    fun getConnectedDevices(): LiveData<List<Device>> {
        return deviceDao.getConnectedDevices()
    }

    fun getUnconnectedDevices(): LiveData<List<Device>> {
        return deviceDao.getUnconnectedDevices()
    }

    fun getOneDevice(id:Int): LiveData<Device>{
        return deviceDao.getOneDevice(id)
    }

    suspend fun update(device: Device) {
        deviceDao.update(device)
    }
    
}
