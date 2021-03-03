package com.github.il4enkodev.househeating.presentation.ui.fragment.readings

import android.app.Application
import android.text.Editable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.github.il4enkodev.househeating.R
import com.github.il4enkodev.househeating.presentation.di.readings.DateFormatter
import com.github.il4enkodev.househeating.presentation.di.readings.ReadingPattern
import com.github.il4enkodev.househeating.presentation.di.readings.TimeFormatter
import com.github.il4enkodev.househeating.presentation.model.Readings
import com.github.il4enkodev.househeating.presentation.ui.events.LiveEvent
import com.github.il4enkodev.househeating.presentation.ui.events.LiveEvent.NotificationStrategy.NOTIFY_FIRST
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class ReadingsEditViewModel @Inject constructor(
        app: Application,
        @DateFormatter private val dateFormatter: DateTimeFormatter,
        @TimeFormatter private val timeFormatter: DateTimeFormatter,
        @ReadingPattern private val pattern: Pattern
): AndroidViewModel(app) {

    companion object {
        private const val TAG = "ReadingsEditViewModel"
    }

    val readings = MutableLiveData(Readings(Readings.Type.START))

    private val dateTime = MutableLiveData(readings.value?.time ?: LocalDateTime.now())
    val timeText: LiveData<String> = dateTime.map { timeFormatter.format(it) }
    val dateText: LiveData<String> = dateTime.map { dateFormatter.format(it) }

    val hint: LiveData<String> = readings.map { readings ->
        when (readings.type) {
            Readings.Type.START -> app.getString(R.string.readings_edit_hint_start)
            Readings.Type.END -> app.getString(R.string.readings_edit_hint_end)
        }
    }

    private val saveAllowed = MutableLiveData(false)
    fun saveAllowed(): LiveData<Boolean> = saveAllowed

    private val navigationEvents = LiveEvent<ReadingsNavEvent<*>>(NOTIFY_FIRST)
    fun navigationEvents(): LiveData<ReadingsNavEvent<*>> = navigationEvents

    fun readingChanged(data: Editable) {
        val valid = pattern.matcher(data).matches()
        saveAllowed.value = valid
        if (valid)
            readings.value = readings.value?.copy(value=data.toString().toDouble())
    }

    fun timeClicked() {
        dateTime.value?.toLocalTime()?.let {
            navigationEvents.push(ReadingsNavEvent.EditTime(it))
        }
    }

    fun dateClicked() {
        dateTime.value?.toLocalDate()?.let {
            navigationEvents.push(ReadingsNavEvent.EditDate(it))
        }
    }

    fun saveClicked() {
        readings.value = readings.value!!.copy(time=dateTime.value!!)
        navigationEvents.push(ReadingsNavEvent.Close)
    }

    fun dateSet(date: LocalDate) {
        dateTime.value = dateTime.value?.with(date)
    }

    fun timeSet(time: LocalTime) {
        dateTime.value = dateTime.value?.with(time)
    }

}