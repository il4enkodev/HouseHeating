package com.github.il4enkodev.househeating.presentation.ui.fragment.readings

import android.app.Application
import androidx.lifecycle.*
import com.github.il4enkodev.househeating.R
import com.github.il4enkodev.househeating.presentation.di.qualifier.DateFormatter
import com.github.il4enkodev.househeating.presentation.di.qualifier.ReadingPattern
import com.github.il4enkodev.househeating.presentation.di.qualifier.TimeFormatter
import com.github.il4enkodev.househeating.presentation.model.ReadingModel
import com.github.il4enkodev.househeating.presentation.ui.events.LiveEvent
import com.github.il4enkodev.househeating.presentation.ui.events.LiveEvent.NotificationStrategy.NOTIFY_FIRST
import com.github.il4enkodev.househeating.presentation.ui.events.ViewRequest
import com.github.il4enkodev.househeating.presentation.ui.events.ViewResult
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjuster
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

    private val readings = MutableLiveData<ReadingModel>()

    val hint: LiveData<String> = readings.map { readings ->
        when (readings.type) {
            ReadingModel.Type.START -> app.getString(R.string.readings_edit_hint_start)
            ReadingModel.Type.END -> app.getString(R.string.readings_edit_hint_end)
        }
    }

    val readingText = MediatorLiveData<String>()
    val timeText: LiveData<String> = readings.map { timeFormatter.format(it.time) }
    val dateText: LiveData<String> = readings.map { dateFormatter.format(it.time) }

    fun initialize(model: ReadingModel) {
        readings.value = model
        if (model.value != 0.0)
            readingText.value = model.value.toString()
    }

    fun saveAllowed(): LiveData<Boolean> = readingText.map { text ->
        pattern.matcher(text).matches()
    }

    private val requests = LiveEvent<ViewRequest<*>>(NOTIFY_FIRST)
    fun requests(): LiveData<ViewRequest<*>> = requests

    private val result = LiveEvent<ViewResult.ReadingUpdated>(NOTIFY_FIRST)
    fun result(): LiveData<ViewResult.ReadingUpdated> = result

    fun onViewResult(result: ViewResult<*>): Unit = when (result) {
        is ViewResult.DateUpdated -> updateDateTime(result.content)
        is ViewResult.TimeUpdated -> updateDateTime(result.content)
        else -> Unit
    }

    fun timeClicked() {
        readings.value?.time?.toLocalTime()?.let {
            requests.push(ViewRequest.EditTime(it))
        }
    }

    fun dateClicked() {
        readings.value?.time?.toLocalDate()?.let {
            requests.push(ViewRequest.EditDate(it))
        }
    }

    fun saveClicked() {
        readings.value = readings.value!!.copy(value=readingText.value!!.toDouble())
        result.push(ViewResult.ReadingUpdated(readings.value))
    }

    private fun updateDateTime(adjuster: TemporalAdjuster?) {
        if (adjuster == null) return
        readings.value = readings.value!!.let {
            it.copy(time=it.time.with(adjuster))
        }
    }

}