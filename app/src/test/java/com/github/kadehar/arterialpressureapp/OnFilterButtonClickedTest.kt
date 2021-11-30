package com.github.kadehar.arterialpressureapp

import com.github.kadehar.arterialpressureapp.base.previousPeriod
import com.github.kadehar.arterialpressureapp.feature.arterial_pressure_list.ui.DataEvent
import com.github.kadehar.arterialpressureapp.feature.arterial_pressure_list.ui.UiEvent
import com.github.kadehar.arterialpressureapp.feature.arterial_pressure_list.ui.model.APListItems
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class OnFilterButtonClickedTest : TestBase() {
    @Before
    fun processLoadDataEvent() {
        viewModel.processUiEvent(DataEvent.LoadData)
    }

    @Test
    fun `OnFilterButtonClicked-Today`() {
        val list = listOf(
            APListItems.ArterialPressure(
                id = "1",
                morning = "1",
                evening = "1",
                timestamp = previousPeriod(days = -1).time
            )
        )

        viewModel.processUiEvent(UiEvent.OnFilterButtonClicked(0))
        val viewState = captureViewState()

        Assert.assertEquals(list, viewState.arterialPressureListShown)
    }

    @Test
    fun `OnFilterButtonClicked-TwoWeeks`() {
        val list = listOf(
            APListItems.ArterialPressure(
                id = "1",
                morning = "1",
                evening = "1",
                timestamp = previousPeriod(days = -1).time
            ),
            APListItems.ArterialPressure(
                id = "2",
                morning = "2",
                evening = "2",
                timestamp = previousPeriod(days = 1).time
            )
        )

        viewModel.processUiEvent(UiEvent.OnFilterButtonClicked(1))
        val viewState = captureViewState()

        Assert.assertEquals(list, viewState.arterialPressureListShown)
    }

    @Test
    fun `OnFilterButtonClicked-OneMonth`() {
        val list = listOf(
            APListItems.ArterialPressure(
                id = "1",
                morning = "1",
                evening = "1",
                timestamp = previousPeriod(days = -1).time
            ),
            APListItems.ArterialPressure(
                id = "2",
                morning = "2",
                evening = "2",
                timestamp = previousPeriod(days = 1).time
            )
        )

        viewModel.processUiEvent(UiEvent.OnFilterButtonClicked(2))
        val viewState = captureViewState()

        Assert.assertEquals(list, viewState.arterialPressureListShown)
    }

    @Test
    fun `OnFilterButtonClicked-ThreeMonths`() {
        val list = listOf(
            APListItems.ArterialPressure(
                id = "1",
                morning = "1",
                evening = "1",
                timestamp = previousPeriod(days = -1).time
            ),
            APListItems.ArterialPressure(
                id = "2",
                morning = "2",
                evening = "2",
                timestamp = previousPeriod(days = 1).time
            ),
            APListItems.ArterialPressure(
                "3",
                "3",
                "3",
                previousPeriod(months = 1).time
            )
        )

        viewModel.processUiEvent(UiEvent.OnFilterButtonClicked(3))
        val viewState = captureViewState()

        Assert.assertEquals(list, viewState.arterialPressureListShown)
    }

    @Test
    fun `OnFilterButtonClicked-SixMonths`() {
        val list = listOf(
            APListItems.ArterialPressure(
                id = "1",
                morning = "1",
                evening = "1",
                timestamp = previousPeriod(days = -1).time
            ),
            APListItems.ArterialPressure(
                id = "2",
                morning = "2",
                evening = "2",
                timestamp = previousPeriod(days = 1).time
            ),
            APListItems.ArterialPressure(
                "3",
                "3",
                "3",
                previousPeriod(months = 1).time
            ),
            APListItems.ArterialPressure(
                "4",
                "4",
                "4",
                previousPeriod(months = 3).time
            )
        )

        viewModel.processUiEvent(UiEvent.OnFilterButtonClicked(4))
        val viewState = captureViewState()

        Assert.assertEquals(list, viewState.arterialPressureListShown)
    }
}