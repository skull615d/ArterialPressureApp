package com.github.kadehar.arterialpressureapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.github.kadehar.arterialpressureapp.data.PressureRepositoryFake
import com.github.kadehar.arterialpressureapp.domain.ArterialPressureInteractor
import com.github.kadehar.arterialpressureapp.feature.arterial_pressure_list.ui.ArterialPressureListViewModel
import com.github.kadehar.arterialpressureapp.feature.arterial_pressure_list.ui.ViewState
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.mockito.kotlin.*

open class TestBase {
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
    fun setUp() {
        val router: Router = mock()
        interactor = ArterialPressureInteractor(PressureRepositoryFake())
        viewModel = ArterialPressureListViewModel(interactor, router)

        viewModel.viewState.observeForever(viewStateObserver)
    }

    fun captureViewState(): ViewState = capture {
        verify(viewStateObserver, atLeastOnce()).onChanged(it.capture())

    }

    private inline fun <reified T : Any> capture(invokeCaptor: (KArgumentCaptor<T>) -> Unit): T {
        val captor = argumentCaptor<T>()
        invokeCaptor(captor)
        return captor.lastValue
    }
}