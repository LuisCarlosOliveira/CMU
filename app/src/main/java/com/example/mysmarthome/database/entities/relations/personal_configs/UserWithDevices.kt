package com.example.mysmarthome.database.entities.relations.personal_configs

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.mysmarthome.database.entities.*

data class UserWithDevices(

    @Embedded
    val user: User,
    @Relation(
        parentColumn = "idUser",
        entityColumn = "idDevice",
        associateBy = Junction(PersonalConfigs::class)
    )
    val devices: List<Device>

){ }