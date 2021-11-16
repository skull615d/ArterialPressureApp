package com.github.kadehar.arterialpressureapp.di

import androidx.room.Room
import com.github.kadehar.arterialpressureapp.ArterialPressureDatabase
import com.github.kadehar.arterialpressureapp.data.PressureRepository
import com.github.kadehar.arterialpressureapp.data.PressureRepositoryImpl
import com.github.kadehar.arterialpressureapp.data.local.PressureDao
import com.github.kadehar.arterialpressureapp.domain.ArterialPressureInteractor
import org.koin.android.ext.koin.androidContext
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
}