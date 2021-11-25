package com.github.kadehar.arterialpressureapp.base

import java.util.*

inline fun <reified T> attempt(func: () -> T): Either<Throwable, T> = try {
    Either.Right(func.invoke())
} catch (e: Throwable) {
    Either.Left(e)
}

fun today(): Date {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    return calendar.time
}

fun twoWeeks(): Date {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, -14)
    return calendar.time
}

fun oneMonth(): Date = getMonths(-1)

fun threeMonths(): Date = getMonths(-3)

fun sixMonths(): Date = getMonths(-6)

private fun getMonths(offset: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.MONTH, offset)
    calendar.set(Calendar.DAY_OF_MONTH, 1)
    return calendar.time
}