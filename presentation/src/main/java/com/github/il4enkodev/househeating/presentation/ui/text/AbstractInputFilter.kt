package com.github.il4enkodev.househeating.presentation.ui.text

import android.text.InputFilter
import android.text.Spanned

abstract class AbstractInputFilter: InputFilter {

    private val builder = StringBuilder()

    protected abstract fun filter(text: String): Boolean

    override fun filter(source: CharSequence?, start: Int, end: Int,
                        dest: Spanned, dstart: Int, dend: Int): CharSequence? {
        builder.apply {
            clear()
            append(dest, 0, dstart)
            append(source, start, end)
            append(dest, dend, dest.length)
        }

        return if (filter(builder.toString())) null else ""
    }
}