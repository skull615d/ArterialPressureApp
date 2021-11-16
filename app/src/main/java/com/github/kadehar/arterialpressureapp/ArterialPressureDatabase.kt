package com.github.kadehar.arterialpressureapp

import androidx.room.RoomDatabase
import com.github.kadehar.arterialpressureapp.data.local.PressureDao

abstract class ArterialPressureDatabase : RoomDatabase() {
    abstract val dao: PressureDao

    companion object {
        const val DATABASE_NAME = "ap_db"
    }
}