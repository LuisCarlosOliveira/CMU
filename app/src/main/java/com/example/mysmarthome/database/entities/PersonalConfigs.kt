package com.example.mysmarthome.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["idUser", "idDevice"])
data class PersonalConfigs(

    val idUser: Int,
    val idDevice: Int

)
