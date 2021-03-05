package com.github.il4enkodev.househeating.presentation.ui.text

import java.util.regex.Pattern

class RegexInputFilter(private val pattern: Pattern): AbstractInputFilter() {

    override fun filter(text: String): Boolean {
        val matcher = pattern.matcher(text)
        return matcher.matches() || matcher.hitEnd()
    }
}