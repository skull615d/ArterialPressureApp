package com.github.kadehar.arterialpressureapp.feature.arterial_pressure_list.ui

import androidx.annotation.StringRes
import com.github.kadehar.arterialpressureapp.R

sealed class DateFilter(@StringRes val string: Int, val isSelected: Boolean) {
    data class Today(val value: Boolean) : DateFilter(R.string.ap_chip_text_today, value)
    data class TwoWeeks(val value: Boolean) : DateFilter(R.string.ap_chip_text_two_weeks, value)
    data class OneMonth(val value: Boolean) : DateFilter(R.string.ap_chip_text_month, value)
    data class ThreeMonths(val value: Boolean) : DateFilter(R.string.ap_chip_text_three_months, value)
    data class SixMonths(val value: Boolean) : DateFilter(R.string.ap_chip_text_six_months, value)
}