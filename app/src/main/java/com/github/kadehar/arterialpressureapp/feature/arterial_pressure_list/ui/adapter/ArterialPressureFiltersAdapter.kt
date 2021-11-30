package com.github.kadehar.arterialpressureapp.feature.arterial_pressure_list.ui.adapter

import com.github.kadehar.arterialpressureapp.R
import com.github.kadehar.arterialpressureapp.databinding.ApFilterItemBinding
import com.github.kadehar.arterialpressureapp.feature.arterial_pressure_list.ui.DateFilter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun arterialPressureFiltersAdapterDelegate(
    onItemClick: (Int) -> Unit
) = adapterDelegateViewBinding<DateFilter, DateFilter, ApFilterItemBinding>(
    { layoutInflater, parent ->
        ApFilterItemBinding.inflate(layoutInflater, parent, false)
    }
) {
    binding.root.setOnClickListener {
        onItemClick(adapterPosition)
    }

    bind {
        binding.apply {
            apFilter.text = getString(item.string)
            apFilter.isSelected = item.isSelected
            if (item.isSelected) {
                apFilter.setTextColor(getColor(R.color.white))
            } else {
                apFilter.setTextColor(getColor(R.color.black))
            }
        }
    }
}