package com.github.kadehar.arterialpressureapp.feature.arterial_pressure_list.ui

import com.github.kadehar.arterialpressureapp.base.BaseViewModel
import com.github.kadehar.arterialpressureapp.base.Event
import com.github.kadehar.arterialpressureapp.base.nav.Screens
import com.github.kadehar.arterialpressureapp.base.yesterday
import com.github.kadehar.arterialpressureapp.domain.ArterialPressureInteractor
import com.github.kadehar.arterialpressureapp.feature.arterial_pressure_list.ui.model.APListItems
import com.github.terrakok.cicerone.Router
import java.text.SimpleDateFormat
import java.util.*

class ArterialPressureListViewModel(
    private val interactor: ArterialPressureInteractor,
    private val router: Router
) :
    BaseViewModel<ViewState>() {
    override fun initialViewState() =
        ViewState(
            arterialPressureList = emptyList(),
            arterialPressureListShown = emptyList(),
            errorMessage = null,
            dateFilterList = listOf(
                DateFilter.Today(false),
                DateFilter.TwoWeeks(false),
                DateFilter.OneMonth(false),
                DateFilter.ThreeMonths(false),
                DateFilter.SixMonths(false)
            )
        )

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
                    arterialPressureListShown = event.arterialPressureList,
                    errorMessage = null
                )
            }
            is UiEvent.OnAddArterialPressureButtonClicked -> {
                router.navigateTo(Screens.arterialPressureDetailsScreen())
            }
            is UiEvent.OnArterialPressureItemClicked -> {
                router.navigateTo(Screens.arterialPressureDetailsScreen(event.arterialPressure))
            }
            is UiEvent.OnFilterButtonClicked -> {
                return previousState.copy(
                    arterialPressureListShown = previousState
                        .arterialPressureList.filter {
                            when (it) {
                                is APListItems.ArterialPressure -> {
                                    val date = Date(it.timestamp)
                                    date.after(yesterday())
                                }
                                else -> false
                            }
                        },
                    dateFilterList = previousState.dateFilterList.map {
                        when(it) {
                            is DateFilter.OneMonth -> {
                                it.copy(false)
                            }
                            is DateFilter.SixMonths -> {
                                it.copy(false)
                            }
                            is DateFilter.ThreeMonths -> {
                                it.copy(false)
                            }
                            is DateFilter.Today -> {
                                it.copy(true)
                            }
                            is DateFilter.TwoWeeks -> {
                                it.copy(false)
                            }
                        }
                    }
                )
            }
        }
        return null
    }
}