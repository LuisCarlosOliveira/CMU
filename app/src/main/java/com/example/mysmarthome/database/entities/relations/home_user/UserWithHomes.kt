package com.example.mysmarthome.database.entities.relations.home_user

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.mysmarthome.database.entities.Home
import com.example.mysmarthome.database.entities.User
import com.example.mysmarthome.database.entities.User_Home

data class UserWithHomes(

    @Embedded
    val user: User,
    @Relation(
        parentColumn = "idUser",
        entityColumn = "idHome",
        associateBy = Junction(User_Home::class)
    )
    val homes: List<Home>

) { }