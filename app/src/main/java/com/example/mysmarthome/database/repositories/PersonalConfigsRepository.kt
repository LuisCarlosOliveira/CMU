package com.example.mysmarthome.database.repositories

import androidx.lifecycle.LiveData
import com.example.mysmarthome.database.dao.PersonalConfigsDAO
import com.example.mysmarthome.database.entities.PersonalConfigs

class PersonalConfigsRepository(val personalConfigsDAO: PersonalConfigsDAO) {

    fun getPersonalConfigs(): LiveData<List<PersonalConfigs>> {
        return personalConfigsDAO.getPersonalConfigs()
    }

    suspend fun insert(personalConfigs: PersonalConfigs) {
        personalConfigsDAO.insert(personalConfigs)
    }

    suspend fun update(personalConfigs: PersonalConfigs) {
        personalConfigsDAO.insert(personalConfigs)
    }

    suspend fun delete(personalConfigs: PersonalConfigs) {
        personalConfigsDAO.delete(personalConfigs)
    }
}