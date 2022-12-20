package com.example.mysmarthome.database.entities.relations.home_user

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.mysmarthome.database.entities.Home
import com.example.mysmarthome.database.entities.User

data class HomeWithUsers(

    @Embedded
    val home: Home,
    @Relation(
        parentColumn = "idHome",
        entityColumn = "idUserHome",
    )
    val users: List<User>

) { }