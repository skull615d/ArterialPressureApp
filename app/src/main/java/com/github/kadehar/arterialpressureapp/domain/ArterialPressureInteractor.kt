package com.github.kadehar.arterialpressureapp.domain

import com.github.kadehar.arterialpressureapp.base.attempt
import com.github.kadehar.arterialpressureapp.data.PressureRepository
import com.github.kadehar.arterialpressureapp.domain.model.ArterialPressure

class ArterialPressureInteractor(private val repository: PressureRepository) {
    suspend fun addNewRecord(arterialPressure: ArterialPressure) =
        repository.addNewRecord(arterialPressure)

    suspend fun updateRecord(arterialPressure: ArterialPressure) =
        repository.updateRecord(arterialPressure)

    suspend fun getAllRecords() =
        attempt {
            repository.getAllRecords()
        }

    suspend fun getRecordById(id: String) =
        attempt {
            repository.getRecordById(id)
        }

    suspend fun deleteRecord(arterialPressure: ArterialPressure) =
        attempt {
            repository.deleteRecord(arterialPressure)
        }
}