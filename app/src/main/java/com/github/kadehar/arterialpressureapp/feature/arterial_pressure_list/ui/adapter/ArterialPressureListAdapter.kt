package com.github.kadehar.arterialpressureapp.feature.arterial_pressure_list.ui.adapter

import com.github.kadehar.arterialpressureapp.databinding.ApHintItemBinding
import com.github.kadehar.arterialpressureapp.databinding.ApItemBinding
import com.github.kadehar.arterialpressureapp.feature.arterial_pressure_list.ui.model.APListItems
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun arterialPressureListAdapterDelegate(
    onItemClick: (arterialPressure: APListItems.ArterialPressure) -> Unit
) = adapterDelegateViewBinding<APListItems.ArterialPressure, APListItems, ApItemBinding>(
    { layoutInflater, parent ->
        ApItemBinding.inflate(layoutInflater, parent, false)
    }
) {
    binding.root.setOnClickListener {
        onItemClick(item)
    }

    bind {
        binding.apply {
            tvMorningData.text = item.morning
            tvEveningData.text = item.evening
        }
    }
}


fun arterialPressureHintAdapterDelegate() =
    adapterDelegateViewBinding<APListItems.Hint, APListItems, ApHintItemBinding>({
            layoutInflater, parent ->
        ApHintItemBinding.inflate(layoutInflater, parent, false)
    }) {
        bind {
            binding.apply {
                tvApHint.text = item.text
            }
        }
    }
