package com.github.kadehar.arterialpressureapp.feature.arterial_pressure_details.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.kadehar.arterialpressureapp.R
import com.github.kadehar.arterialpressureapp.base.MaskWatcher
import com.github.kadehar.arterialpressureapp.databinding.FragmentArterialPressureDetailsBinding
import com.github.kadehar.arterialpressureapp.feature.arterial_pressure_list.ui.model.APListItems
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class ArterialPressureDetailsFragment : Fragment(R.layout.fragment_arterial_pressure_details) {
    companion object {
        private const val AP_KEY = "arterialPressure"
        fun newInstance(arterialPressure: APListItems.ArterialPressure?) =
            ArterialPressureDetailsFragment().apply {
                arguments = bundleOf(AP_KEY to arterialPressure)
            }
    }

    private val binding: FragmentArterialPressureDetailsBinding by viewBinding(
        FragmentArterialPressureDetailsBinding::bind
    )

    private val arterialPressure: APListItems.ArterialPressure? by lazy {
        requireArguments().getParcelable(AP_KEY)
    }

    private val viewModel by viewModel<ArterialPressureDetailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            arterialPressure?.let {
                apDetailsMorningText.setText(it.morning)
                apDetailsEveningText.setText(it.evening)
            }

            with(apDetailsMorningText) {
                addTextChangedListener(MaskWatcher.buildDefault())
                setOnEditorActionListener { _, action, _ ->
                    if (action == EditorInfo.IME_ACTION_DONE) {
                        val imm: InputMethodManager = requireActivity()
                            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(this.windowToken, 0)
                        return@setOnEditorActionListener true
                    }
                    return@setOnEditorActionListener false
                }
            }

            with(apDetailsEveningText) {
                addTextChangedListener(MaskWatcher.buildDefault())
                setOnEditorActionListener { _, action, _ ->
                    if (action == EditorInfo.IME_ACTION_DONE) {
                        val imm: InputMethodManager = requireActivity()
                            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(this.windowToken, 0)
                        return@setOnEditorActionListener true
                    }
                    return@setOnEditorActionListener false
                }
            }

            apDetailsSaveButton.setOnClickListener {
                viewModel.processUiEvent(
                    UiEvent.OnArterialPressureSaveButtonClicked(
                        arterialPressure?.id,
                        apDetailsMorningText.text.toString(),
                        apDetailsEveningText.text.toString(),
                        Date().time
                    )
                )
            }
        }
    }
}
