package com.example.mysmarthome.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreUtil(private val context: Context) {

    companion object {

        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")

        val THEME_KEY = booleanPreferencesKey("theme")

        val SAVE_ENERGY_KEY = booleanPreferencesKey("energy")

    }

    fun getTheme(isSystemDarkTheme: Boolean): Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[THEME_KEY] ?: isSystemDarkTheme
        }

    fun getThemeState(): Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[THEME_KEY] ?: false
        }

    suspend fun saveTheme(isDarkThemeEnabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDarkThemeEnabled
        }
    }

    fun getSaveEnergy(): Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[SAVE_ENERGY_KEY] ?: false
        }

    suspend fun save_SaveEnergy(isSaveEnergy: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[SAVE_ENERGY_KEY] = isSaveEnergy
        }
    }
}