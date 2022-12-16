package com.example.mysmarthome.database.entities.relations.device_typedevice

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mysmarthome.database.entities.Device

data class DeviceWithPlugs(

    @Embedded
    val device: Device,
    @Relation(
        parentColumn = "idDevice",
        entityColumn = "idDevicePlug",
    )
    val plugs: List<Plug>

){ }