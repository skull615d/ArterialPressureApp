package com.github.kadehar.arterialpressureapp.domain

import com.github.kadehar.arterialpressureapp.base.attempt
import com.github.kadehar.arterialpressureapp.data.PressureRepository
import com.github.kadehar.arterialpressureapp.domain.model.ArterialPressure
import com.github.kadehar.arterialpressureapp.feature.arterial_pressure_list.ui.model.APListItems

class ArterialPressureInteractor(private val repository: PressureRepository) {
    suspend fun addNewRecord(arterialPressure: ArterialPressure) =
        repository.addNewRecord(arterialPressure)

    suspend fun updateRecord(arterialPressure: ArterialPressure) =
        repository.updateRecord(arterialPressure)

    suspend fun getAllRecords() =
        attempt {
            repository.getAllRecords().map { ap ->
                APListItems.ArterialPressure(
                    id = ap.id,
                    morning = ap.morning,
                    evening = ap.evening,
                    timestamp = ap.timestamp
                )
            }
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