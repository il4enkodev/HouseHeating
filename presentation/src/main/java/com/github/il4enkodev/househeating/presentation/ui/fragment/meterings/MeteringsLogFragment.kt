package com.github.il4enkodev.househeating.presentation.ui.fragment.meterings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.il4enkodev.househeating.R

class MeteringsLogFragment : Fragment() {

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
        // TODO: Use the ViewModel
    }

}