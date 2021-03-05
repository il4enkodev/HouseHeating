package com.github.il4enkodev.househeating.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

@Parcelize
data class ReadingModel(val type: Type,
                        val value: Double? = null,
                        val time: LocalDateTime = LocalDateTime.now()
): Parcelable {
    enum class Type { START, END }

    @IgnoredOnParcel val isAtTheStart = type == Type.START
    @IgnoredOnParcel val isAtTheEnd = type == Type.END

    override fun toString(): String {
        val time = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(time)
        val type = type.name.toLowerCase(Locale.ROOT)
        return "ReadingModel {$type - $time ($value)}"
    }
}