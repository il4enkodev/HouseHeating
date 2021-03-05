package com.github.il4enkodev.househeating.presentation.ui

import android.text.InputFilter
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@BindingAdapter(value = ["datetime", "formatter"], requireAll = true)
fun datetime(view: TextView, time: LocalDateTime, formatter: DateTimeFormatter) {
    view.text = formatter.format(time)
}

@BindingAdapter("filter")
fun filter(view: EditText, inputFilter: InputFilter) {
    view.filters = arrayOf(inputFilter)
}