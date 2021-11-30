package com.github.kadehar.arterialpressureapp.feature.arterial_pressure_list.ui

import com.github.kadehar.arterialpressureapp.R
import com.github.kadehar.arterialpressureapp.base.*
import com.github.kadehar.arterialpressureapp.base.nav.Screens
import com.github.kadehar.arterialpressureapp.domain.ArterialPressureInteractor
import com.github.kadehar.arterialpressureapp.feature.arterial_pressure_list.ui.model.APListItems
import com.github.terrakok.cicerone.Router
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
                DateFilter(R.string.ap_chip_text_today, false, DateFilterType.Today),
                DateFilter(R.string.ap_chip_text_two_weeks, false, DateFilterType.TwoWeeks),
                DateFilter(R.string.ap_chip_text_month, false, DateFilterType.OneMonth),
                DateFilter(R.string.ap_chip_text_three_months, false, DateFilterType.ThreeMonths),
                DateFilter(R.string.ap_chip_text_six_months, false, DateFilterType.SixMonths)
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
                val selectedFilterIndex = previousState.dateFilterList.indexOfFirst { it.isSelected }

                return previousState.copy(
                    arterialPressureListShown = previousState
                        .arterialPressureList.filterIndexed { _, it ->
                            when (it) {
                                is APListItems.ArterialPressure -> {
                                    val date = Date(it.timestamp)
                                    if (selectedFilterIndex == event.index) {
                                        true
                                    } else {
                                        when (event.index) {
                                            0 -> date.after(previousPeriod())
                                            1 -> date.after(previousPeriod(days = 14))
                                            2 -> date.after(previousPeriod(months = 1))
                                            3 -> date.after(previousPeriod(months = 3))
                                            else -> date.after(previousPeriod(months = 6))
                                        }
                                    }
                                }
                                else -> false
                            }
                        },
                    dateFilterList = previousState.dateFilterList.mapIndexed { index, it ->
                        it.copy(isSelected = index == event.index && !it.isSelected)
                    }
                )
            }
        }
        return null
    }
}