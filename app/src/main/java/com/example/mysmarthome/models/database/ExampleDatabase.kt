package com.example.mysmarthome.models.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
//import com.example.mysmarthome.models.dao.ExampleDao
//import com.example.mysmarthome.models.entities.Example

/*
@Database(entities = [Example::class], version = 1, exportSchema = false)
abstract class ExampleDatabase : RoomDatabase() {

    abstract fun contactDao(): ExampleDao
    companion object {

        @Volatile
        private var INSTANCE: ExampleDatabase? = null

        fun getDatabase(context: Context): ExampleDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExampleDatabase::class.java,
                    "contact-database"
                ).allowMainThreadQueries().build()  //tirar o allowMainThreadQueries!!!!!!
                INSTANCE = instance
                instance
            }
        }
    }
}
*/