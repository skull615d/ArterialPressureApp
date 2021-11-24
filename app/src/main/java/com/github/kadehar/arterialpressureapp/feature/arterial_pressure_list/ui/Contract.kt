package com.github.kadehar.arterialpressureapp.feature.arterial_pressure_list.ui

import com.github.kadehar.arterialpressureapp.base.Event
import com.github.kadehar.arterialpressureapp.feature.arterial_pressure_list.ui.model.APListItems

data class ViewState(
    val arterialPressureList: List<APListItems>,
    val arterialPressureListShown: List<APListItems>,
    val errorMessage: String?,
    val dateFilterList: List<DateFilter>
)

sealed class UiEvent : Event {
    object OnAddArterialPressureButtonClicked : UiEvent()
    data class OnArterialPressureItemClicked(val arterialPressure: APListItems.ArterialPressure) :
        UiEvent()
    data class OnFilterButtonClicked(val index: Int) : UiEvent()
}

sealed class DataEvent : Event {
    object LoadData : DataEvent()
    data class SuccessfulDataLoad(val arterialPressureList: List<APListItems>) : DataEvent()
    data class OnDataLoadError(val errorMessage: String) : DataEvent()
}