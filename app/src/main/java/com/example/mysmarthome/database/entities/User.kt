package com.example.mysmarthome.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.mysmarthome.enums.TypeMember

@Entity (indices = [Index(value = ["email"],unique = true)])
data class User(
        val name: String,

        val email: String,

        val typeMember: String,

        val password: String,

        val contact: Int,

        var idUserHome: Int

){
        @PrimaryKey (autoGenerate = true)
        var idUser: Int=0
}
