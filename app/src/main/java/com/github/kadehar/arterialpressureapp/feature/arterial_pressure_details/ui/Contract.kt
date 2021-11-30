package com.github.kadehar.arterialpressureapp.feature.arterial_pressure_details.ui

import com.github.kadehar.arterialpressureapp.base.Event

object ViewState

sealed class UiEvent : Event {
    data class OnArterialPressureSaveButtonClicked(
        val id: String?,
        val morning: String,
        val evening: String,
        val timestamp: Long
        ) : UiEvent()
}