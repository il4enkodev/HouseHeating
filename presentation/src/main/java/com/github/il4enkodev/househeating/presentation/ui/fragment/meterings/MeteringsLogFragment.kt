package com.github.il4enkodev.househeating.presentation.ui.fragment.meterings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.il4enkodev.househeating.R
import com.github.il4enkodev.househeating.presentation.model.ReadingModel
import com.github.il4enkodev.househeating.presentation.ui.FabViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MeteringsLogFragment: Fragment() {

    companion object {
        private const val TAG = "MeteringsLogFragment"
    }

    private val fabViewModel: FabViewModel by activityViewModels()
    private val viewModel: MeteringsLogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.meterings_log_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fabViewModel.clicks().observe(viewLifecycleOwner) {
            val reading = ReadingModel(ReadingModel.Type.START)
            val action = MeteringsLogFragmentDirections.actionAddReadings(reading)
            findNavController().navigate(action)
        }
    }
}