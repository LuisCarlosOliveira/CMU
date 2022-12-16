package com.example.mysmarthome.database.entities.relations.device_typedevice

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mysmarthome.database.entities.Device

data class DeviceWithBlinds(

    @Embedded
    val device: Device,
    @Relation(
        parentColumn = "idDevice",
        entityColumn = "idDeviceBlind",
    )
    val blinds: List<Blind>

){ }