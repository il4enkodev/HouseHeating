package com.github.il4enkodev.househeating.presentation.ui.text

open class DecimalNumberFilter(val format: String): AbstractInputFilter() {

    override fun filter(text: String): Boolean {
        val maxLength = format.length
        if (text.length > maxLength) return false

        // define integer and fraction part digits count
        val required = format.split(".").map { it.length }
        val provided = text.split(".").map { it.length }

        if (provided.count() > required.count()) return false
        provided.forEachIndexed { index, count ->
            if (count > required[index]) return false
        }

        return true
    }
}