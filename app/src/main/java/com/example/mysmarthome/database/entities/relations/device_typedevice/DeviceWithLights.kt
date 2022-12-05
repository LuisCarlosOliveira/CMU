package com.example.mysmarthome.database.entities.relations.device_typedevice

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mysmarthome.database.entities.Device
import com.example.mysmarthome.database.entities.Light

data class DeviceWithLights(

    @Embedded
    val device: Device,
    @Relation(
        parentColumn = "idDevice",
        entityColumn = "idDeviceLight",
    )
    val lights: List<Light>

){ }