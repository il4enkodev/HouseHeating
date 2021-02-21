package com.github.il4enkodev.househeating.presentation.ui.fragment.readings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.il4enkodev.househeating.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ReadingsEditDialog: BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.readings_edit_dialog, container, false)
    }
}