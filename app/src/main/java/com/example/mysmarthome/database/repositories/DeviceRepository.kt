package com.example.mysmarthome.database.repositories

import androidx.lifecycle.LiveData
import com.example.mysmarthome.database.dao.DevicesDAO
import com.example.mysmarthome.database.entities.Device
import com.example.mysmarthome.database.entities.relations.device_division.DivisionWithDevices
import com.example.mysmarthome.enums.TypeDevice

class DeviceRepository(val deviceDao: DevicesDAO) {

    fun getConnectedDevices(): LiveData<List<Device>> {
        return deviceDao.getConnectedDevices()
    }

    fun getUnconnectedDevices(): LiveData<List<Device>> {
        return deviceDao.getUnconnectedDevices()
    }

    fun getOneDevice(idDevice: Int): LiveData<Device> {
        return deviceDao.getOneDevice(idDevice)
    }

    fun getDevices(): LiveData<List<Device>> {
        return deviceDao.getDevices()
    }

    fun getConectedDevicesByDivision(idDivision: Int): LiveData<DivisionWithDevices> {
        return deviceDao.getConectedDevicesByDivision(idDivision)
    }

    fun getDevicesByTypeDevice(typeDevice: String): LiveData<List<Device>> {
        return deviceDao.getDevicesByTypeDevice(typeDevice)
    }

    fun getDeviceByPort(port: Int): LiveData<Device> {
        return deviceDao.getDeviceByPort(port)
    }

    suspend fun insert(device: Device) {
        deviceDao.insert(device)
    }

    suspend fun update(device: Device) {
        deviceDao.update(device)
    }
}
