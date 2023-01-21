package com.example.mysmarthome.database.view_models

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mysmarthome.database.database.MySmartHomeDatabase
import com.example.mysmarthome.database.entities.Device
import com.example.mysmarthome.database.entities.Division
import com.example.mysmarthome.database.entities.Home
import com.example.mysmarthome.database.entities.relations.device_division.DivisionWithDevices
import com.example.mysmarthome.database.repositories.DeviceRepository
import com.example.mysmarthome.enums.TypeDevice
import com.example.mysmarthome.retrofit.data_models.blind.Blind
import com.example.mysmarthome.retrofit.data_models.blind.BlindMeters
import com.example.mysmarthome.retrofit.data_models.light.Light
import com.example.mysmarthome.retrofit.data_models.light.LightMeters
import com.example.mysmarthome.retrofit.data_models.plug.Plug
import com.example.mysmarthome.retrofit.data_models.plug.PlugMeters
import com.example.mysmarthome.retrofit.helper.RetrofitHelper
import com.example.mysmarthome.retrofit.shelly_api.blind.BlindAPI
import com.example.mysmarthome.retrofit.shelly_api.light.LightAPI
import com.example.mysmarthome.retrofit.shelly_api.plug.PlugAPI
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DevicesViewModel(application: Application) : AndroidViewModel(application) {

    val repository: DeviceRepository

    val allDevices: LiveData<List<Device>>
    val dbF : FirebaseFirestore

    val divisionViewModal: DivisionsViewModel
    // Blind
    var posit: MutableLiveData<Int>

    var getBlindStatus: MutableLiveData<Blind>
    var getBlind: MutableLiveData<Blind>
    var getBlindActions: MutableLiveData<Blind>
    var getBlindMeterOpen: MutableLiveData<BlindMeters>
    var getBlindMeterClose: MutableLiveData<BlindMeters>

    // Plug
    var getPlugStatus: MutableLiveData<Plug>
    var getPlug: MutableLiveData<Plug>
    var getPlugTimer: MutableLiveData<Plug>
    var getPlugMeter: MutableLiveData<PlugMeters>

    // Light
    var getLightStatus: MutableLiveData<Light>
    var getLight: MutableLiveData<Light>
    var getLightTimer: MutableLiveData<Light>
    var getLightActionsColorMode: MutableLiveData<Light>
    var getLightActionsWhiteMode: MutableLiveData<Light>
    var getLightMeter: MutableLiveData<LightMeters>

    init {
        val db = MySmartHomeDatabase.getDatabase(application)

        val restAPILight = RetrofitHelper.getInstance(3002).create(LightAPI::class.java)
        val restAPIPlug = RetrofitHelper.getInstance(3001).create(PlugAPI::class.java)
        val restAPIBlind = RetrofitHelper.getInstance(3000).create(BlindAPI::class.java)

        divisionViewModal = DivisionsViewModel(application)
        repository = DeviceRepository(db.getDeviceDao(), restAPILight, restAPIPlug, restAPIBlind)
        allDevices = repository.getDevices()
        dbF = Firebase.firestore

        posit = MutableLiveData<Int>(null)

        getBlindStatus = MutableLiveData<Blind>(null)
        getBlind = MutableLiveData<Blind>(null)
        getBlindActions = MutableLiveData<Blind>(null)
        getBlindMeterOpen = MutableLiveData<BlindMeters>(null)
        getBlindMeterClose = MutableLiveData<BlindMeters>(null)

        getPlugStatus = MutableLiveData<Plug>(null)
        getPlug = MutableLiveData<Plug>(null)
        getPlugTimer = MutableLiveData<Plug>(null)
        getPlugMeter = MutableLiveData<PlugMeters>(null)

        getLightStatus = MutableLiveData<Light>(null)
        getLight = MutableLiveData<Light>(null)
        getLightTimer = MutableLiveData<Light>(null)
        getLightActionsColorMode = MutableLiveData<Light>(null)
        getLightActionsWhiteMode = MutableLiveData<Light>(null)
        getLightMeter = MutableLiveData<LightMeters>(null)

    }

    // Blind

    fun getBlindStatus() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val blindStatusResponse = repository.getBlindStatus()
                if (blindStatusResponse.isSuccessful) {
                    val bodyResponse = blindStatusResponse.body()!!
                    getBlindStatus.postValue(bodyResponse)
                    posit.postValue(bodyResponse.rollers[0].current_pos)
                } else {
                    println(
                        "Sem Resposta do Servidor..." + "\n" + blindStatusResponse.errorBody().toString()
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }


    fun getBlind() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val blindResponse = repository.getBlind()
                if (blindResponse.isSuccessful) {
                    val bodyResponse = blindResponse.body()!!
                    getBlind.postValue(bodyResponse)
                    posit.postValue(bodyResponse.rollers[0].current_pos)
                } else {
                    println(
                        "Sem Resposta do Servidor..." + "\n" + blindResponse.errorBody().toString()
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    fun getBlindActions(go: String, roller_pos: Int?) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val blindActionsResponse =
                    repository.getBlindActions(go, roller_pos)
                Log.d("B RESPONSE", blindActionsResponse.toString())
                if (blindActionsResponse.isSuccessful) {
                    var bodyBlindActionResponse = blindActionsResponse.body()!!
                    Log.d("bodyBlindActionResponse", bodyBlindActionResponse.toString())
                    getBlindActions.postValue(bodyBlindActionResponse)
                    posit.postValue(bodyBlindActionResponse.rollers[0].current_pos)
                    Log.d("getBlindActions", getBlindActions.toString())
                } else {
                    println(
                        "Sem Resposta do Servidor..." + "\n" + blindActionsResponse.errorBody()
                            .toString()
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    fun getBlindMeterOpen() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val blindMeterOpenResponse = repository.getBlindMeter(0)
                if (blindMeterOpenResponse.isSuccessful) {
                    Log.d("B RESPONSE", blindMeterOpenResponse.toString())
                    val bodyBlindMeterResponse = blindMeterOpenResponse.body()!!
                    Log.d("getBlindMeterOpen", bodyBlindMeterResponse.toString())
                    getBlindMeterOpen.postValue(bodyBlindMeterResponse)

                } else {
                    println(
                        "Sem Resposta do Servidor..." + "\n" + blindMeterOpenResponse.errorBody().toString()
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    fun getBlindMeterClose() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val blindMeterCloseResponse = repository.getBlindMeter(1)
                if (blindMeterCloseResponse.isSuccessful) {
                    Log.d("B RESPONSE", blindMeterCloseResponse.toString())
                    val bodyBlindMeterResponse = blindMeterCloseResponse.body()!!
                    Log.d("getBlindMeterClose", bodyBlindMeterResponse.toString())
                    getBlindMeterClose.postValue(bodyBlindMeterResponse)

                } else {
                    println(
                        "Sem Resposta do Servidor..." + "\n" + blindMeterCloseResponse.errorBody().toString()
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    // Plug

    fun getPlugStatus() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val plugStatusResponse = repository.getPlugStatus()
                if (plugStatusResponse.isSuccessful) {
                    val bodyResponse = plugStatusResponse.body()!!
                    getPlugStatus.postValue(bodyResponse)
                } else {
                    println(
                        "Sem Resposta do Servidor..." + "\n" + plugStatusResponse.errorBody().toString()
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    fun getPlug() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val plugResponse = repository.getPlug()
                if (plugResponse.isSuccessful) {
                    val bodyResponse = plugResponse.body()!!
                    getPlug.postValue(bodyResponse)
                } else {
                    println(
                        "Sem Resposta do Servidor..." + "\n" + plugResponse.errorBody().toString()
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    fun getPlugTimer(turn: String, timer: Int?) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val plugTimerResponse =
                    repository.getPlugTimer(turn, timer)
                Log.d("P RESPONSE", plugTimerResponse.toString())
                if (plugTimerResponse.isSuccessful) {
                    var bodyPlugActionResponse = plugTimerResponse.body()!!
                    Log.d("bodyPlugActionResponse", bodyPlugActionResponse.toString())
                    getPlugTimer.postValue(bodyPlugActionResponse)
                    Log.d("getPlugActions", getPlugTimer.toString())
                } else {
                    println(
                        "Sem Resposta do Servidor..." + "\n" + plugTimerResponse.errorBody()
                            .toString()
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    fun getPlugMeter() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val plugMeterResponse = repository.getPlugMeter(0)
                if (plugMeterResponse.isSuccessful) {
                    val bodyPlugMeterResponse = plugMeterResponse.body()!!
                    getPlugMeter.postValue(bodyPlugMeterResponse)
                } else {
                    println(
                        "Sem Resposta do Servidor..." + "\n" + plugMeterResponse.errorBody().toString()
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    // Light

    fun getLightStatus() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val lightStatusResponse = repository.getLightStatus()
                if (lightStatusResponse.isSuccessful) {
                    val bodyResponse = lightStatusResponse.body()!!
                    getLightStatus.postValue(bodyResponse)
                } else {
                    println(
                        "Sem Resposta do Servidor..." + "\n" + lightStatusResponse.errorBody().toString()
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    fun getLight() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val lightResponse = repository.getLight()
                if (lightResponse.isSuccessful) {
                    val bodyResponse = lightResponse.body()!!
                    getLight.postValue(bodyResponse)
                } else {
                    println(
                        "Sem Resposta do Servidor..." + "\n" + lightResponse.errorBody().toString()
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    fun getLightTimer(turn: String, timer: Int?) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val lightTimerResponse = repository.getLightTimer(turn, timer)
                if (lightTimerResponse.isSuccessful) {
                    val bodyResponse = lightTimerResponse.body()!!
                    getLightTimer.postValue(bodyResponse)
                } else {
                    println(
                        "Sem Resposta do Servidor..." + "\n" + lightTimerResponse.errorBody()
                            .toString()
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
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
            try {
                val lightActionsResponse =
                    repository.getLightActionsWhiteMode(turn, mode, white, brightness.toInt(), timer)
                Log.d("L RESPONSE", lightActionsResponse.toString())
                if (lightActionsResponse.isSuccessful) {
                    var bodyLightActionResponse = lightActionsResponse.body()!!
                    Log.d("bodyLightActionResponse", bodyLightActionResponse.toString())
                    getLightActionsWhiteMode.postValue(bodyLightActionResponse)
                    Log.d("getLightActions", getLightActionsWhiteMode.toString())
                } else {
                    println(
                        "Sem Resposta do Servidor..." + "\n" + lightActionsResponse.errorBody()
                            .toString()
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
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
            try {
                val lightActionsResponse =
                    repository.getLightActionsColorMode(
                        turn,
                        mode,
                        red,
                        green,
                        blue,
                        white,
                        gain.toInt(),
                        timer
                    )
                Log.d("L RESPONSE", lightActionsResponse.toString())
                if (lightActionsResponse.isSuccessful) {
                    var bodyLightActionResponse = lightActionsResponse.body()!!
                    Log.d("bodyLightActionResponse", bodyLightActionResponse.toString())
                    getLightActionsColorMode.postValue(bodyLightActionResponse)
                    Log.d("getLightActions", getLightActionsColorMode.toString())
                } else {
                    println(
                        "Sem Resposta do Servidor..." + "\n" + lightActionsResponse.errorBody()
                            .toString()
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    fun getLightMeter() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val lightMeterResponse = repository.getLightMeter(0)
                if (lightMeterResponse.isSuccessful) {
                    Log.d("L RESPONSE", lightMeterResponse.toString())
                    val bodyLightMeterResponse = lightMeterResponse.body()!!
                    Log.d("getLightMeter", bodyLightMeterResponse.toString())
                    getLightMeter.postValue(bodyLightMeterResponse)

                } else {
                    println(
                        "Sem Resposta do Servidor..." + "\n" + lightMeterResponse.errorBody().toString()
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }


    fun insertDevice(device: Device, divisionID: Int, home: Home) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(device)
            val div = divisionViewModal.getOneDivisionF(divisionID)
            val homeRef = dbF.collection("homes").document(home.idF)
            val divisionRef = homeRef.collection("divisions").document(div.idF)
            val deviceRef = divisionRef.collection("devices").document()
            deviceRef.set(device)
                .addOnSuccessListener { Log.d(ContentValues.TAG, "Device added with ID: ${deviceRef.id}") }
                .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error adding device", e) }

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