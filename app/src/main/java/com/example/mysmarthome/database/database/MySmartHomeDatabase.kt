package com.example.mysmarthome.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mysmarthome.database.dao.*
import com.example.mysmarthome.database.entities.*

@Database(
    entities = [User::class, Device::class, Division::class, Home::class, PersonalConfigs::class],
    version = 2
)
abstract class MySmartHomeDatabase : RoomDatabase() {

    abstract fun getUsersDao(): UsersDAO
    abstract fun getDeviceDao(): DevicesDAO
    abstract fun getDivisionDao(): DivisionsDAO
    abstract fun getHomeDao(): HomesDAO
    abstract fun getPersonalConfigsDao(): PersonalConfigsDAO

    companion object {

        @Volatile
        private var INSTANCE: MySmartHomeDatabase? = null

        fun getDatabase(context: Context): MySmartHomeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MySmartHomeDatabase::class.java,
                    "mysmarthome-database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}