package com.example.mysmarthome.models.dao

import androidx.room.*
import com.example.mysmarthome.models.entities.Example

@Dao
interface ExampleDao{
    @Query("select * from Example")
    fun getAllExamples():List<Example>

    @Query("select * from Example where example = :example")
    fun getOneExample(example: Int): Example

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExample(example: Example):Unit

    @Delete
    fun deleteExample(example: Example):Unit

}