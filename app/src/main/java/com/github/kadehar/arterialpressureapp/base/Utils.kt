package com.github.kadehar.arterialpressureapp.base

import java.util.*

inline fun <reified T> attempt(func: () -> T): Either<Throwable, T> = try {
    Either.Right(func.invoke())
} catch (e: Throwable) {
    Either.Left(e)
}

fun yesterday(): Date {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DATE, -1)
    return calendar.time
}