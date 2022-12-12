package com.example.mysmarthome.database.entities.relations.personal_configs

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.example.mysmarthome.database.entities.*

data class DeviceWithUsers(

    @Embedded
    val device: Device,
    @Relation(
        parentColumn = "idDevice",
        entityColumn = "idUser",
        associateBy = Junction(PersonalConfigs::class)
    )
    val users: List<User>

){ }