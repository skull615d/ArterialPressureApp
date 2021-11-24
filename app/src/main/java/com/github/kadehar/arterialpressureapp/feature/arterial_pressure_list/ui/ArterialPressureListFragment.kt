package com.github.kadehar.arterialpressureapp.feature.arterial_pressure_list.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.kadehar.arterialpressureapp.R
import com.github.kadehar.arterialpressureapp.base.setAdapterAndCleanupOnDetachFromWindow
import com.github.kadehar.arterialpressureapp.base.setData
import com.github.kadehar.arterialpressureapp.base.setThrottledClickListener
import com.github.kadehar.arterialpressureapp.databinding.FragmentArterialPressureListBinding
import com.github.kadehar.arterialpressureapp.feature.arterial_pressure_list.ui.adapter.arterialPressureFiltersAdapterDelegate
import com.github.kadehar.arterialpressureapp.feature.arterial_pressure_list.ui.adapter.arterialPressureListAdapterDelegate
import com.google.android.material.chip.Chip
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArterialPressureListFragment : Fragment(R.layout.fragment_arterial_pressure_list) {
    companion object {
        fun newInstance() = ArterialPressureListFragment()
    }

    private val binding: FragmentArterialPressureListBinding by viewBinding(
        FragmentArterialPressureListBinding::bind
    )
    private val viewModel by viewModel<ArterialPressureListViewModel>()
    private val apListAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ListDelegationAdapter(
            arterialPressureListAdapterDelegate {
                viewModel.processUiEvent(UiEvent.OnArterialPressureItemClicked(it))
            }
        )
    }

    private val apFiltersAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ListDelegationAdapter(
            arterialPressureFiltersAdapterDelegate {
                viewModel.processUiEvent(UiEvent.OnFilterButtonClicked(it))
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        viewModel.viewState.observe(viewLifecycleOwner, ::render)
        with(binding) {
            apDetailsCreateButton.setThrottledClickListener {
                viewModel.processUiEvent(UiEvent.OnAddArterialPressureButtonClicked)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.processUiEvent(DataEvent.LoadData)
    }

    private fun initAdapter() {
        with(binding) {
            arterialPressureList.apply {
                setAdapterAndCleanupOnDetachFromWindow(apListAdapter)
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
            }
            arterialPressureFilters.apply {
                setAdapterAndCleanupOnDetachFromWindow(apFiltersAdapter)
            }
        }
    }

    private fun render(viewState: ViewState) {
        apListAdapter.setData(viewState.arterialPressureListShown)
        apFiltersAdapter.setData(viewState.dateFilterList)
    }
}