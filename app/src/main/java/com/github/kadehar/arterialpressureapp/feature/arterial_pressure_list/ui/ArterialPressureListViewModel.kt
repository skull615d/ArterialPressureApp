package com.github.kadehar.arterialpressureapp.feature.arterial_pressure_list.ui

import com.github.kadehar.arterialpressureapp.base.BaseViewModel
import com.github.kadehar.arterialpressureapp.base.Event
import com.github.kadehar.arterialpressureapp.domain.ArterialPressureInteractor

class ArterialPressureListViewModel(private val interactor: ArterialPressureInteractor) :
    BaseViewModel<ViewState>() {
    override fun initialViewState() =
        ViewState(arterialPressureList = emptyList(), errorMessage = null)

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is DataEvent.LoadData -> {
                interactor.getAllRecords().fold(
                    onError = {
                        processDataEvent(DataEvent.OnDataLoadError(it.localizedMessage ?: ""))
                    },
                    onSuccess = {
                        processDataEvent(DataEvent.SuccessfulDataLoad(it))
                    }
                )
            }
            is DataEvent.SuccessfulDataLoad -> {
                return previousState.copy(
                    arterialPressureList = event.arterialPressureList,
                    errorMessage = null
                )
            }
        }
        return null
    }
}