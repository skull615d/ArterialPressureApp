package com.github.kadehar.arterialpressureapp.data

import com.github.kadehar.arterialpressureapp.domain.model.ArterialPressure

interface PressureRepository {
    suspend fun addNewRecord(arterialPressure: ArterialPressure)
    suspend fun updateRecord(arterialPressure: ArterialPressure)
    suspend fun getAllRecords(): List<ArterialPressure>
    suspend fun getRecordById(id: String): ArterialPressure
    suspend fun deleteRecord(arterialPressure: ArterialPressure)
}