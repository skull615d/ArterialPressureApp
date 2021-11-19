package com.github.kadehar.arterialpressureapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.kadehar.arterialpressureapp.data.local.PressureEntity.Companion.TABLE_NAME
import java.util.*

@Entity(tableName = TABLE_NAME)
data class PressureEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "morning") val morning: String,
    @ColumnInfo(name = "evening") val evening: String,
    @ColumnInfo(name = "timestamp") val timestamp: Long
) {
    companion object {
        const val TABLE_NAME = "pressure"
    }
}
