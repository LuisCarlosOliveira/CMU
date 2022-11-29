package com.example.mysmarthome.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mysmarthome.enums.TypeMember

@Entity
data class User(

        @PrimaryKey val idUser: Int,
        val name: String,
        val email: String,
        val typeMember: TypeMember,
        val password: String,
        val contact: Int

)
