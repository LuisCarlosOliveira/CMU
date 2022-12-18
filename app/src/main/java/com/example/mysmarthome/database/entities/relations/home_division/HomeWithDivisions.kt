package com.example.mysmarthome.database.entities.relations.home_division

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.example.mysmarthome.database.entities.Division
import com.example.mysmarthome.database.entities.Home

data class HomeWithDivisions(

    @Embedded
    val home: Home,
    @Relation(
        parentColumn = "idHome",
        entityColumn = "idDivisionHome",
    )
    val divisions: List<Division>

) { }