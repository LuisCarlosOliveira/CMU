package com.example.mysmarthome.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Home(
    val name: String,
    @Embedded val address: Address?
){
    @PrimaryKey (autoGenerate = true)
    var idHome: Int=0
}
