package com.example.mysmarthome.database.entities.relations.device_typedevice

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.example.mysmarthome.database.entities.Device
import com.example.mysmarthome.database.entities.Plug

data class DeviceWithPlugs(

    @Embedded
    val device: Device,
    @Relation(
        parentColumn = "idDevice",
        entityColumn = "idDevicePlug",
    )
    val plugs: List<Plug>

){ }