package com.github.kadehar.arterialpressureapp.feature.arterial_pressure_list.ui.model

import com.github.kadehar.arterialpressureapp.base.Item

sealed class APListItems : Item {
    data class Hint(val text: String, val status: Boolean) : APListItems()
    data class ArterialPressure(
        val id: String,
        val morning: String,
        val evening: String,
        val timestamp: Long
    ) : APListItems()
}
