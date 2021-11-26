package com.github.kadehar.arterialpressureapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.github.kadehar.arterialpressureapp.data.PressureRepositoryFake
import com.github.kadehar.arterialpressureapp.domain.ArterialPressureInteractor
import com.github.kadehar.arterialpressureapp.domain.model.ArterialPressure
import com.github.kadehar.arterialpressureapp.feature.arterial_pressure_list.ui.ArterialPressureListViewModel
import com.github.kadehar.arterialpressureapp.feature.arterial_pressure_list.ui.DataEvent
import com.github.kadehar.arterialpressureapp.feature.arterial_pressure_list.ui.ViewState
import com.github.kadehar.arterialpressureapp.feature.arterial_pressure_list.ui.model.APListItems
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.kotlin.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineRule = CoroutineRule()

    private val viewStateObserver: Observer<ViewState> = mock()

    lateinit var viewModel: ArterialPressureListViewModel
    lateinit var interactor: ArterialPressureInteractor

    @Before
    fun init() {

        interactor = ArterialPressureInteractor(PressureRepositoryFake())
        val router: Router = mock()

        viewModel = ArterialPressureListViewModel(interactor, router)

        viewModel.viewState.observeForever(viewStateObserver)
    }


    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    @Test
    fun `LoadData-SuccessfulDataLoad`() {

        // Arrange
        val list = listOf(
            APListItems.ArterialPressure("1", "1", "1", 100),
            APListItems.ArterialPressure("2", "2", "2", 200)
        )

        // Act
        viewModel.processUiEvent(DataEvent.LoadData)
        val viewState = captureViewState()

        // Assert
        assertEquals(list[0], viewState.arterialPressureList[0])
        assertEquals(list[1], viewState.arterialPressureList[1])

    }

    private fun captureViewState(): ViewState = capture {
        verify(viewStateObserver, atLeastOnce()).onChanged(it.capture())

    }

    inline fun <reified T : Any> capture(invokeCaptor: (KArgumentCaptor<T>) -> Unit): T {
        val captor = argumentCaptor<T>()
        invokeCaptor(captor)
        return captor.lastValue
    }

}


