package com.github.kadehar.arterialpressureapp

import com.github.kadehar.arterialpressureapp.base.previousPeriod
import com.github.kadehar.arterialpressureapp.feature.arterial_pressure_list.ui.DataEvent
import com.github.kadehar.arterialpressureapp.feature.arterial_pressure_list.ui.model.APListItems
import org.junit.Assert
import org.junit.Test

class LoadDataTest : TestBase() {
    @Test
    fun `LoadData-SuccessfulDataLoad`() {
        val list = listOf(
            APListItems.ArterialPressure(
                id = "1",
                morning = "1",
                evening = "1",
                timestamp = previousPeriod().time
            ),
            APListItems.ArterialPressure(
                id = "2",
                morning = "2",
                evening = "2",
                timestamp = previousPeriod(days = 1).time
            )
        )

        viewModel.processUiEvent(DataEvent.LoadData)
        val viewState = captureViewState()

        Assert.assertEquals(list[0], viewState.arterialPressureList[0])
        Assert.assertEquals(list[1], viewState.arterialPressureList[1])
    }
}