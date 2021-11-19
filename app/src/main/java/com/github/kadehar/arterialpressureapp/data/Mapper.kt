package com.github.kadehar.arterialpressureapp.data

import com.github.kadehar.arterialpressureapp.data.local.PressureEntity
import com.github.kadehar.arterialpressureapp.domain.model.ArterialPressure
import com.github.kadehar.arterialpressureapp.feature.arterial_pressure_list.ui.model.APListItems
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
        id = id ?: UUID.randomUUID().toString(),
        morning = morning,
        evening = evening,
        timestamp = timestamp
    )

fun List<PressureEntity>.toArterialPressureList(): List<ArterialPressure> =
    map { pressureEntity ->
        pressureEntity.toArterialPressure()
    }

fun APListItems.ArterialPressure.toArterialPressure(): ArterialPressure =
    ArterialPressure(
        id = id,
        morning = morning,
        evening = evening,
        timestamp = timestamp
    )