package com.example.mysmarthome.database.repositories

import androidx.lifecycle.LiveData
import com.example.mysmarthome.database.dao.DevicesDAO
import com.example.mysmarthome.database.entities.Device
import com.example.mysmarthome.database.entities.relations.device_division.DivisionWithDevices
import com.example.mysmarthome.enums.TypeDevice
import com.example.mysmarthome.retrofit.data_models.blind.Blind
import com.example.mysmarthome.retrofit.data_models.light.Light
import com.example.mysmarthome.retrofit.data_models.plug.Plug
import com.example.mysmarthome.retrofit.shelly_api.blind.BlindAPI
import com.example.mysmarthome.retrofit.shelly_api.light.LightAPI
import com.example.mysmarthome.retrofit.shelly_api.plug.PlugAPI
import retrofit2.Response

class DeviceRepository(
    val deviceDao: DevicesDAO,
    val restAPILight: LightAPI,
    val restAPIPlug: PlugAPI,
    val restAPIBlind: BlindAPI
) {

    /*
    suspend fun getBlind(): Response<Blind> {
        return this.restAPIBlind.getBlind()
    }
     */

    /*
    suspend fun getBlindAction(
        go: String,
        roller_pos: Int
    ): Response<Blind> {
        return this.restAPIBlind.getBlindActions(go, roller_pos)
    }
    */

    /*
    suspend fun getPlug(): Response<Plug> {
        return this.restAPIPlug.getPlug()
    }
    */

    /*
    suspend fun getPlugAction(
        turn: String,
        timer: Int
    ): Response<Plug> {
        return this.restAPIPlug.getPlugActions(turn, timer)
    }
    */

    suspend fun getLight(): Response<Light> {
        return this.restAPILight.getLight()
    }

    suspend fun getLightTimer(turn: String, timer: Int?): Response<Light> {
        return this.restAPILight.getLightTimer(turn, timer)
    }

    suspend fun getLightActionsWhiteMode(
        turn: String,
        mode: String,
        white: Int,
        brightness: Float,
        timer: Int?
    ): Response<Light> {
        return this.restAPILight.getLightActionsWhiteMode(turn, mode, white, brightness, timer)
    }

    suspend fun getLightActionsColorMode(
        turn: String,
        mode: String,
        red: Int,
        green: Int,
        blue: Int,
        white: Int,
        gain: Float,
        timer: Int?
    ): Response<Light> {
        return this.restAPILight.getLightActionsColorMode(
            turn,
            mode,
            red,
            green,
            blue,
            white,
            gain,
            timer
        )
    }

    fun getOneDevice(idDevice: Int): LiveData<Device> {
        return deviceDao.getOneDevice(idDevice)
    }

    suspend fun insert(device: Device) {
        deviceDao.insert(device)
    }

    fun getDevices(): LiveData<List<Device>> {
        return deviceDao.getDevices()
    }

    suspend fun update(device: Device) {
        deviceDao.update(device)
    }

    fun getConnectedDevices(): LiveData<List<Device>> {
        return deviceDao.getConnectedDevices()
    }

    fun getUnconnectedDevices(): LiveData<List<Device>> {
        return deviceDao.getUnconnectedDevices()
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

}
