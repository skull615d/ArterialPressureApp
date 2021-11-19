package com.github.kadehar.arterialpressureapp.di

import androidx.room.Room
import com.github.kadehar.arterialpressureapp.ArterialPressureDatabase
import com.github.kadehar.arterialpressureapp.data.PressureRepository
import com.github.kadehar.arterialpressureapp.data.PressureRepositoryImpl
import com.github.kadehar.arterialpressureapp.data.local.PressureDao
import com.github.kadehar.arterialpressureapp.domain.ArterialPressureInteractor
import com.github.kadehar.arterialpressureapp.feature.arterial_pressure_details.ui.ArterialPressureDetailsViewModel
import com.github.kadehar.arterialpressureapp.feature.arterial_pressure_list.ui.ArterialPressureListViewModel
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<ArterialPressureDatabase> {
        Room.databaseBuilder(
            androidContext(),
            ArterialPressureDatabase::class.java,
            ArterialPressureDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single<PressureDao> {
        get<ArterialPressureDatabase>().dao
    }

    single<PressureRepository> {
        PressureRepositoryImpl(get<PressureDao>())
    }

    single<ArterialPressureInteractor> {
        ArterialPressureInteractor(get<PressureRepository>())
    }

    viewModel<ArterialPressureListViewModel> {
        ArterialPressureListViewModel(get<ArterialPressureInteractor>(), get<Router>())
    }

    viewModel<ArterialPressureDetailsViewModel> {
        ArterialPressureDetailsViewModel(get<ArterialPressureInteractor>(), get<Router>())
    }
}

val navModule = module {
    single<Cicerone<Router>> {
        Cicerone.create(Router())
    }

    single<NavigatorHolder> {
        get<Cicerone<Router>>().getNavigatorHolder()
    }

    single<Router> {
        get<Cicerone<Router>>().router
    }
}