package com.example.mysmarthome.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PersonalConfigs(
    @PrimaryKey val id: Int
)
