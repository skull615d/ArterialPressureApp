package com.github.kadehar.arterialpressureapp.feature.arterial_pressure_details.ui

import com.github.kadehar.arterialpressureapp.base.BaseViewModel
import com.github.kadehar.arterialpressureapp.base.Event
import com.github.kadehar.arterialpressureapp.domain.ArterialPressureInteractor
import com.github.kadehar.arterialpressureapp.domain.model.ArterialPressure
import com.github.terrakok.cicerone.Router

class ArterialPressureDetailsViewModel(
    private val interactor: ArterialPressureInteractor,
    private val router: Router
) : BaseViewModel<ViewState>() {
    override fun initialViewState() = ViewState

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UiEvent.OnArterialPressureSaveButtonClicked -> {
                interactor.addNewRecord(ArterialPressure(
                    id = event.id,
                    morning = event.morning,
                    evening = event.evening,
                    timestamp = event.timestamp
                ))
                router.exit()
            }
        }
        return null
    }
}