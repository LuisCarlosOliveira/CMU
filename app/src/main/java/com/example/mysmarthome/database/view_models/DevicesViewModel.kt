package com.example.mysmarthome.database.view_models

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mysmarthome.database.database.MySmartHomeDatabase
import com.example.mysmarthome.database.entities.Device
import com.example.mysmarthome.database.entities.relations.device_division.DivisionWithDevices
import com.example.mysmarthome.database.repositories.DeviceRepository
import com.example.mysmarthome.enums.TypeDevice
import com.example.mysmarthome.retrofit.data_models.blind.Blind
import com.example.mysmarthome.retrofit.data_models.light.Light
import com.example.mysmarthome.retrofit.data_models.plug.Plug
import com.example.mysmarthome.retrofit.helper.RetrofitHelper
import com.example.mysmarthome.retrofit.shelly_api.blind.BlindAPI
import com.example.mysmarthome.retrofit.shelly_api.light.LightAPI
import com.example.mysmarthome.retrofit.shelly_api.plug.PlugAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DevicesViewModel(application: Application) : AndroidViewModel(application) {

    val repository: DeviceRepository
    val allDevices: LiveData<List<Device>>
    var getBlind: MutableLiveData<Blind>
    var getBlindActions: MutableLiveData<Blind>
    var getPlug: MutableLiveData<Plug>
    var getPlugActions: MutableLiveData<Plug>
    var getLight: MutableLiveData<Light>
    var getLightTimer: MutableLiveData<Light>
    var getLightActionsColorMode: MutableLiveData<Light>
    var getLightActionsWhiteMode: MutableLiveData<Light>

    init {
        val db = MySmartHomeDatabase.getDatabase(application)
        val restAPILight = RetrofitHelper.getInstance(3000).create(LightAPI::class.java)
        val restAPIPlug = RetrofitHelper.getInstance(3001).create(PlugAPI::class.java)
        val restAPIBlind = RetrofitHelper.getInstance(3002).create(BlindAPI::class.java)
        repository = DeviceRepository(db.getDeviceDao(), restAPILight, restAPIPlug, restAPIBlind)
        allDevices = repository.getDevices()
        getBlind = MutableLiveData<Blind>(null)
        getBlindActions = MutableLiveData<Blind>(null)
        getPlug = MutableLiveData<Plug>(null)
        getPlugActions = MutableLiveData<Plug>(null)
        getLight = MutableLiveData<Light>(null)
        getLightTimer = MutableLiveData<Light>(null)
        getLightActionsColorMode = MutableLiveData<Light>(null)
        getLightActionsWhiteMode = MutableLiveData<Light>(null)
    }

    /*
    fun getBlind(){
        viewModelScope.launch(Dispatchers.IO) {
            val blindResponse = repository.getBlind()
            if (blindResponse.isSuccessful) {
                val bodyResponse = blindResponse.body()!!
                getBlind.postValue(bodyResponse)
            } else {
                blindResponse.errorBody()
            }
        }
    }
    */

    /*
    fun getBlindActions(go: String, roller_pos:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val blindActionsResponse =
                repository.getBlindAction(go, roller_pos)
            Log.d("B RESPONSE", blindActionsResponse.toString())
            if (blindActionsResponse.isSuccessful) {
                var bodyBlindActionResponse = blindActionsResponse.body()!!
                Log.d("bodyBlindActionResponse", bodyBlindActionResponse.toString())
                getBlindActions.postValue(bodyBlindActionResponse)
                Log.d("getBlindActions", getBlindActions.toString())
            } else {
                blindActionsResponse.errorBody()
            }
        }
    }
    */

    /*
    fun getPlug(){
        viewModelScope.launch(Dispatchers.IO) {
            val plugResponse = repository.getPlug()
            if (plugResponse.isSuccessful) {
                val bodyResponse = plugResponse.body()!!
                getPlug.postValue(bodyResponse)
            } else {
                plugResponse.errorBody()
            }
        }
    }
    */

    /*
    fun getPlugActions(turn: String, timer:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val plugActionsResponse =
                repository.getPlugAction(turn, timer)
            Log.d("P RESPONSE", plugActionsResponse.toString())
            if (plugActionsResponse.isSuccessful) {
                var bodyPlugActionResponse = plugActionsResponse.body()!!
                Log.d("bodyPlugActionResponse", bodyPlugActionResponse.toString())
                getPlugActions.postValue(bodyPlugActionResponse)
                Log.d("getPlugActions", getPlugActions.toString())
            } else {
                plugActionsResponse.errorBody()
            }
        }
    }
    */

    fun getLight() {
        viewModelScope.launch(Dispatchers.IO) {
            val lightResponse = repository.getLight()
            if (lightResponse.isSuccessful) {
                val bodyResponse = lightResponse.body()!!
                getLight.postValue(bodyResponse)
            } else {
                lightResponse.errorBody()
            }
        }
    }

    fun getLightTimer(turn: String, timer: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            val lightTimerResponse = repository.getLightTimer(turn, timer)
            if (lightTimerResponse.isSuccessful) {
                val bodyResponse = lightTimerResponse.body()!!
                getLightTimer.postValue(bodyResponse)
            } else {
                lightTimerResponse.errorBody()
            }
        }
    }

    fun getLightActionsWhiteMode(
        turn: String,
        mode: String,
        cor: MutableList<Int>,
        bright: Float,
        timer: Int?
    ) {
        val white: Int = cor[3]
        val brightness: Float = bright * 100

        viewModelScope.launch(Dispatchers.IO) {
            val lightActionsResponse =
                repository.getLightActionsWhiteMode(turn, mode, white, brightness, timer)
            Log.d("L RESPONSE", lightActionsResponse.toString())
            if (lightActionsResponse.isSuccessful) {
                var bodyLightActionResponse = lightActionsResponse.body()!!
                Log.d("bodyLightActionResponse", bodyLightActionResponse.toString())
                getLightActionsWhiteMode.postValue(bodyLightActionResponse)
                Log.d("getLightActions", getLightActionsWhiteMode.toString())
            } else {
                lightActionsResponse.errorBody()
            }

        }
    }

    fun getLightActionsColorMode(
        turn: String,
        mode: String,
        cor: MutableList<Int>,
        intensity: Float,
        timer: Int?
    ) {
        val red: Int = cor[0]
        val green: Int = cor[1]
        val blue: Int = cor[2]
        val white: Int = cor[3]
        val gain: Float = intensity * 100

        viewModelScope.launch(Dispatchers.IO) {
            val lightActionsResponse =
                repository.getLightActionsColorMode(
                    turn,
                    mode,
                    red,
                    green,
                    blue,
                    white,
                    gain,
                    timer
                )
            Log.d("L RESPONSE", lightActionsResponse.toString())
            if (lightActionsResponse.isSuccessful) {
                var bodyLightActionResponse = lightActionsResponse.body()!!
                Log.d("bodyLightActionResponse", bodyLightActionResponse.toString())
                getLightActionsColorMode.postValue(bodyLightActionResponse)
                Log.d("getLightActions", getLightActionsColorMode.toString())
            } else {
                lightActionsResponse.errorBody()
            }

        }
    }


    fun insertDevice(device: Device) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(device)
        }
    }

    fun getOneDevice(id: Int): LiveData<Device> {
        return repository.getOneDevice(id)
    }

    fun getDevices(): LiveData<List<Device>> {
        return repository.getDevices()
    }

    fun updateDevice(device: Device) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(device)
        }
    }

    fun getConnectedDevices(): LiveData<List<Device>> {
        return repository.getConnectedDevices()
    }

    fun getUnconnectedDevices(): LiveData<List<Device>> {
        return repository.getUnconnectedDevices()
    }

    fun getConectedDevicesByDivision(id: Int): LiveData<DivisionWithDevices> {
        return repository.getConectedDevicesByDivision(id)
    }

    fun getDevicesByTypeDevice(typeDevice: String): LiveData<List<Device>> {
        return repository.getDevicesByTypeDevice(typeDevice)
    }

    fun getDeviceByPort(port: Int): LiveData<Device> {
        return repository.getDeviceByPort(port)
    }
}