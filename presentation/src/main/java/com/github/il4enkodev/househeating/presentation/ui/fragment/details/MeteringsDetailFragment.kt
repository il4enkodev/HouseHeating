package com.github.il4enkodev.househeating.presentation.ui.fragment.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.il4enkodev.househeating.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MeteringsDetailFragment : Fragment() {

    private val viewModel: MeteringsDetailViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.meterings_detail_fragment, container, false)
    }

}