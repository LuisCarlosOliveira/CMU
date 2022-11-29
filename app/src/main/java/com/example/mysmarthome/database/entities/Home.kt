package com.example.mysmarthome.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Home(

    @PrimaryKey val idHome: Int,
    val name: String,
    @Embedded val address: Address?

)
