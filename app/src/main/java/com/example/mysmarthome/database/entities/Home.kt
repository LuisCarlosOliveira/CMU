package com.example.mysmarthome.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Home(
    @Embedded val address: Address?,
    val name: String,
    val idF: String,
){
    @PrimaryKey (autoGenerate = true)
    var idHome: Int=0
}
