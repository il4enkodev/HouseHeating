package com.github.il4enkodev.househeating.presentation.ui.text

import android.text.InputFilter
import android.text.Spanned
import java.util.regex.Pattern

class RegexInputFilter(private val pattern: Pattern): InputFilter {

    private val builder = StringBuilder()

    override fun filter(source: CharSequence?, start: Int, end: Int,
                        dest: Spanned, dstart: Int, dend: Int): CharSequence? {
        builder.apply {
            clear()
            append(dest, 0, dstart)
            append(source, start, end)
            append(dest, dend, dest.length)
        }

        val matcher = pattern.matcher(builder)
        val valid = matcher.matches() || matcher.hitEnd()
        return if (valid) null else ""
    }
}