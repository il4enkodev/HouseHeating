package com.github.il4enkodev.househeating.presentation.ui.events

import android.os.Bundle
import com.github.il4enkodev.househeating.presentation.model.ReadingModel
import com.github.il4enkodev.househeating.presentation.ui.events.ViewRequest.Companion.EDIT_DATE
import com.github.il4enkodev.househeating.presentation.ui.events.ViewRequest.Companion.EDIT_READING
import com.github.il4enkodev.househeating.presentation.ui.events.ViewRequest.Companion.EDIT_TIME
import java.time.LocalDate
import java.time.LocalTime

sealed class ViewResult<R>(val key: String, res: R?): Event<R?>(res) {

    fun toBundle(): Bundle = Bundle().also { toBundle(it) }

    abstract fun toBundle(bundle: Bundle)

    class ReadingUpdated(model: ReadingModel?): ViewResult<ReadingModel?>(EDIT_READING, model) {
        override fun toBundle(bundle: Bundle) {
            bundle.putParcelable(EDIT_READING, content)
        }
    }

    class DateUpdated(date: LocalDate?): ViewResult<LocalDate?>(EDIT_DATE, date) {
        override fun toBundle(bundle: Bundle) {
            bundle.putSerializable(EDIT_DATE, content)
        }
    }

    class TimeUpdated(time: LocalTime?): ViewResult<LocalTime?>(EDIT_TIME, time) {
        override fun toBundle(bundle: Bundle) {
            bundle.putSerializable(EDIT_TIME, content)
        }
    }
}

fun ViewResult.ReadingUpdated.fromBundle(bundle: Bundle): ViewResult.ReadingUpdated? {
    return bundle.getParcelable<ReadingModel>(EDIT_READING)?.let {
        ViewResult.ReadingUpdated(it)
    }
}

fun ViewResult.DateUpdated.fromBundle(bundle: Bundle): ViewResult.DateUpdated? {
    return bundle.getSerializable(EDIT_DATE)?.let {
        ViewResult.DateUpdated(it as LocalDate)
    }
}

fun ViewResult.TimeUpdated.fromBundle(bundle: Bundle): ViewResult.TimeUpdated? {
    return bundle.getSerializable(EDIT_TIME)?.let {
        ViewResult.TimeUpdated(it as LocalTime)
    }
}