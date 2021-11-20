package com.github.kadehar.arterialpressureapp.data.local

import androidx.room.*

@Dao
interface PressureDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewRecord(entity: PressureEntity)

    @Update
    suspend fun updateRecord(entity: PressureEntity)

    @Query("select * from pressure order by timestamp desc")
    suspend fun getAllRecords(): List<PressureEntity>

    @Query("select * from pressure where id = :id")
    suspend fun getRecordById(id: String): PressureEntity

    @Delete
    suspend fun deleteRecord(entity: PressureEntity)
}