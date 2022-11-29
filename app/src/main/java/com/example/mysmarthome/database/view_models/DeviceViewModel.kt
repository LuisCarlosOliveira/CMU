package com.example.mysmarthome.database.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mysmarthome.database.database.MySmartHomeDatabase
import com.example.mysmarthome.database.entities.Device
import com.example.mysmarthome.database.repositories.DeviceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DevicesViewModels(application: Application) : AndroidViewModel(application) {

    val repository: DeviceRepository
    val allDevicesConnected: LiveData<List<Device>>
    val allDevicesUnconnected: LiveData<List<Device>>

    init {
        val db = MySmartHomeDatabase.getDatabase(application)
        repository = DeviceRepository(db.getDeviceDao())
        allDevicesConnected = repository.getConnectedDevices()
        allDevicesUnconnected = repository.getUnconnectedDevices()
    }

    fun getUnconnectedDevices(): LiveData<List<Device>> {
        return repository.getUnconnectedDevices()
    }

    fun getConnectedDevices(): LiveData<List<Device>> {
        return repository.getConnectedDevices()
    }

    fun updateDevice(device: Device) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(device)
        }
    }

    fun getOneDevice(id: Int): LiveData<Device> {
        return repository.getOneDevice(id)
    }

}