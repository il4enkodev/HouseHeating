package com.github.il4enkodev.househeating.presentation.ui.fragment.meterings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.github.il4enkodev.househeating.R
import com.github.il4enkodev.househeating.presentation.ui.FabViewModel

class MeteringsLogFragment : Fragment() {

    private val fabViewModel: FabViewModel by activityViewModels()

    companion object {
        fun newInstance() = MeteringsLogFragment()
    }

    private lateinit var viewModel: MeteringsLogViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.meterings_log_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MeteringsLogViewModel::class.java)
        fabViewModel.clicks().observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Fab clicked", Toast.LENGTH_SHORT).show()
        }
    }

}