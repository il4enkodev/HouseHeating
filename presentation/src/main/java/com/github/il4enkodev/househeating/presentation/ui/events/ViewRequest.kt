package com.github.il4enkodev.househeating.presentation.ui.events

import android.os.Bundle
import com.github.il4enkodev.househeating.presentation.model.ReadingModel
import java.time.LocalDate
import java.time.LocalTime

sealed class ViewRequest<T>(val key: String, content: T): Event<T>(content, sticky=true) {

    companion object {
        const val EDIT_READING = "reading"
        const val EDIT_TIME = "time"
        const val EDIT_DATE = "date"
    }

    fun toBundle(): Bundle = Bundle().also { toBundle(it) }

    abstract fun toBundle(bundle: Bundle)

    class EditReading(model: ReadingModel): ViewRequest<ReadingModel>(EDIT_READING, model) {
        override fun toBundle(bundle: Bundle) {
            bundle.putParcelable(key, content)
        }
    }

    class EditDate(date: LocalDate): ViewRequest<LocalDate>(EDIT_DATE, date) {
        override fun toBundle(bundle: Bundle) {
            bundle.putSerializable(key, content)
        }
    }

    class EditTime(time: LocalTime): ViewRequest<LocalTime>(EDIT_TIME, time) {
        override fun toBundle(bundle: Bundle) {
            bundle.putSerializable(key, content)
        }
    }
}

fun ViewRequest.EditReading.fromBundle(bundle: Bundle): ViewRequest.EditReading? {
    return bundle.getParcelable<ReadingModel>(ViewRequest.EDIT_READING)?.let { model ->
        ViewRequest.EditReading(model)
    }
}

fun ViewRequest.EditDate.fromBundle(bundle: Bundle): ViewRequest.EditDate? {
    return bundle.getSerializable(ViewRequest.EDIT_TIME)?.let { date ->
        ViewRequest.EditDate(date as LocalDate)
    }
}

fun ViewRequest.EditTime.fromBundle(bundle: Bundle): ViewRequest.EditTime? {
    return bundle.getSerializable(ViewRequest.EDIT_TIME)?.let { time ->
        ViewRequest.EditTime(time as LocalTime)
    }
}
