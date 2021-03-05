package com.github.il4enkodev.househeating.presentation.ui.fragment.readings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.github.il4enkodev.househeating.presentation.di.qualifier.ReadingFormat
import com.github.il4enkodev.househeating.presentation.ui.text.DecimalNumberFilter
import java.text.DecimalFormat
import java.util.regex.Pattern
import javax.inject.Inject

class ReadingInputHelper @Inject constructor(@ReadingFormat format: String) {

    val filter = DecimalNumberFilter(format)
    private val formatter = DecimalFormat(format)
    private val pattern = createPattern(format)

    val text = MutableLiveData<String>()
    val number: LiveData<Double?> = text.map {
        if (it.isNotEmpty() && pattern.matcher(it).matches()) formatter.parse(it)?.toDouble()
        else null
    }

    fun set(value: Double?) {
        text.value = value?.let {formatter.format(it)} ?: ""
    }
}

fun createPattern(format: String): Pattern {
    val builder = StringBuilder()
    format.split(".").forEachIndexed { index, part ->
        if (index == 1)
            builder.append("\\.")
        if (index > 1)
            throw IllegalArgumentException(
                    "$index decimal separators in the format string: '$format'")
        for (c in part) {
            builder.append(when (c) {
                '0' -> "\\d"
                '#' -> "\\d?"
                else -> throw IllegalArgumentException(
                        "Illegal char '$c' in the format string: '$format'")
            })
        }
    }
    return Pattern.compile(builder.toString())
}