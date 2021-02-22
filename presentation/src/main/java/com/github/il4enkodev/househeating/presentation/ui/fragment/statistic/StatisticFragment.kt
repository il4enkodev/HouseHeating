package com.github.il4enkodev.househeating.presentation.ui.fragment.statistic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.il4enkodev.househeating.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatisticFragment : Fragment() {

    private val viewModel: StatisticViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.statistic_fragment, container, false)
    }

}