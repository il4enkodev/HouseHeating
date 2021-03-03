package com.github.il4enkodev.househeating.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Readings(val type: Type,
                    val value: Double? = null,
                    val time: LocalDateTime = LocalDateTime.now()
): Parcelable {
    enum class Type { START, END }
}