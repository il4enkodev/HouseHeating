package com.github.il4enkodev.househeating.presentation.ui.fragment.statistic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.il4enkodev.househeating.R

class StatisticFragment : Fragment() {

    companion object {
        fun newInstance() = StatisticFragment()
    }

    private lateinit var viewModel: StatisticViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.statistic_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StatisticViewModel::class.java)
        // TODO: Use the ViewModel
    }

}