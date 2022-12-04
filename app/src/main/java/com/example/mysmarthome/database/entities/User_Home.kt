package com.example.mysmarthome.database.entities

import androidx.room.Entity

@Entity(primaryKeys = ["idUser","idHome"])
data class User_Home(

    val idUser: Int,

    val idHome: Int

){ }