package com.example.mysmarthome.database.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class ThemeViewModel : ViewModel() {

    //DarkMode
    var isDarkThemeEnabled = mutableStateOf(false)
        private set

    fun setTheme(isDarkTheme: Boolean) {
        isDarkThemeEnabled.value = isDarkTheme
    }

    //SaveEnergy
    var isSaveEnergyEnabled = mutableStateOf(false)
        private set

    fun setSaveEnergy(isSaveEnergy: Boolean) {
        isSaveEnergyEnabled.value = isSaveEnergy
    }
}