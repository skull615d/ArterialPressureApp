package com.github.kadehar.arterialpressureapp.data

import com.github.kadehar.arterialpressureapp.base.previousPeriod
import com.github.kadehar.arterialpressureapp.domain.model.ArterialPressure
import java.util.*

class PressureRepositoryFake() : PressureRepository {
    override suspend fun addNewRecord(arterialPressure: ArterialPressure) {
        TODO("Not yet implemented")
    }

    override suspend fun updateRecord(arterialPressure: ArterialPressure) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllRecords(): List<ArterialPressure> {
        return listOf(
            ArterialPressure(
                "1",
                "1",
                "1",
                previousPeriod(days = -1).time
            ),
            ArterialPressure(
                "2",
                "2",
                "2",
                previousPeriod(days = 1).time
            ),
            ArterialPressure(
                "3",
                "3",
                "3",
                previousPeriod(months = 1).time
            ),
            ArterialPressure(
                "4",
                "4",
                "4",
                previousPeriod(months = 3).time
            ),
            ArterialPressure(
                "5",
                "5",
                "5",
                previousPeriod(months = 6).time
            )
        )
    }

    override suspend fun getRecordById(id: String): ArterialPressure {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRecord(arterialPressure: ArterialPressure) {
        TODO("Not yet implemented")
    }
}