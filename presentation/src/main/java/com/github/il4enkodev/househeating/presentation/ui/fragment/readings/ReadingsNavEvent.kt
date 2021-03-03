package com.github.il4enkodev.househeating.presentation.ui.fragment.readings

import com.github.il4enkodev.househeating.presentation.ui.events.NavEvent
import java.time.LocalDate
import java.time.LocalTime

sealed class ReadingsNavEvent<T>(content: T): NavEvent<T>(content) {

    class EditDate(date: LocalDate): ReadingsNavEvent<LocalDate>(date)
    class EditTime(time: LocalTime): ReadingsNavEvent<LocalTime>(time)
    object Close: ReadingsNavEvent<Unit>(Unit)

}