package com.example.mysmarthome.database.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mysmarthome.database.database.MySmartHomeDatabase
import com.example.mysmarthome.database.entities.Device
import com.example.mysmarthome.database.repositories.DeviceRepository
import com.example.mysmarthome.enums.TypeDevice
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DevicesViewModel(application: Application) : AndroidViewModel(application) {

    val repository: DeviceRepository
    val allDevices : LiveData<List<Device>>

    init {
        val db = MySmartHomeDatabase.getDatabase(application)
        repository = DeviceRepository(db.getDeviceDao())
        allDevices= repository.getDevices()
    }

    fun insertDevice(device: Device){
        viewModelScope.launch(Dispatchers.IO){
            repository.insert(device)
        }
    }

    fun getOneDevice(id:Int): LiveData<Device> {
        return repository.getOneDevice(id)
    }

    fun getallDevices(): LiveData<List<Device>> {
        return repository.getDevices()
    }

    fun updateDevice(device: Device) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(device)
        }
    }

    fun getConectedDevices(): LiveData<List<Device>> {
        return repository.getConnectedDevices()
    }

    fun getunconectedDevices(): LiveData<List<Device>> {
        return repository.getUnconnectedDevices()
    }

    fun getConectedDevicesByDevision(id:Int): LiveData<List<Device>> {
        return repository.getConnectedDevicesByDivision(id)
    }

    fun getTypeDevice(id: Int): LiveData<TypeDevice>{
        return repository.getTypeDevice(id)
    }
}