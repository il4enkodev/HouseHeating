package com.github.il4enkodev.househeating.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class ReadingModel(val type: Type,
                        val value: Double = 0.0,
                        val time: LocalDateTime = LocalDateTime.now()
): Parcelable {
    enum class Type { START, END }
}