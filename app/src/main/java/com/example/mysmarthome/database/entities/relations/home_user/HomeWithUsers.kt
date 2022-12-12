package com.example.mysmarthome.database.entities.relations.home_user

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.example.mysmarthome.database.entities.Home
import com.example.mysmarthome.database.entities.User
import com.example.mysmarthome.database.entities.User_Home

data class HomeWithUsers(

    @Embedded
    val home: Home,
    @Relation(
        parentColumn = "idHome",
        entityColumn = "idUser",
        associateBy = Junction(User_Home::class)
    )
    val users: List<User>

) { }