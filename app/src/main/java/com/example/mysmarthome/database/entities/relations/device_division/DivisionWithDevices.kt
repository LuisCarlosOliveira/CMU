package com.example.mysmarthome.database.entities.relations.device_division

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mysmarthome.database.entities.Device
import com.example.mysmarthome.database.entities.Division

data class DivisionWithDevices(

    @Embedded
    val division: Division,
    @Relation(
        parentColumn = "idDivision",
        entityColumn = "idDivisionDevice",
    )
    val devices: List<Device>

) { }