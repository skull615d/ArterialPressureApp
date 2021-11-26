package com.github.kadehar.arterialpressureapp.data

import com.github.kadehar.arterialpressureapp.domain.model.ArterialPressure

class PressureRepositoryFake(): PressureRepository {
    override suspend fun addNewRecord(arterialPressure: ArterialPressure) {
        TODO("Not yet implemented")
    }

    override suspend fun updateRecord(arterialPressure: ArterialPressure) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllRecords(): List<ArterialPressure> {
        return listOf(ArterialPressure("1","1","1",100), ArterialPressure("2","2","2",200))
    }

    override suspend fun getRecordById(id: String): ArterialPressure {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRecord(arterialPressure: ArterialPressure) {
        TODO("Not yet implemented")
    }
}