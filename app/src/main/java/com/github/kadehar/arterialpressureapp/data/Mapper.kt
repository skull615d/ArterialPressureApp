package com.github.kadehar.arterialpressureapp.data

import com.github.kadehar.arterialpressureapp.data.local.PressureEntity
import com.github.kadehar.arterialpressureapp.domain.model.ArterialPressure
import java.util.*

fun PressureEntity.toArterialPressure(): ArterialPressure =
    ArterialPressure(
        id = id,
        morning = morning,
        evening = evening,
        timestamp = timestamp
    )

fun ArterialPressure.toPressureEntity(): PressureEntity =
    PressureEntity(
        id = id,
        morning = morning,
        evening = evening,
        timestamp = timestamp
    )

fun List<PressureEntity>.toArterialPressureList(): List<ArterialPressure> =
    map { pressureEntity ->
        pressureEntity.toArterialPressure()
    }