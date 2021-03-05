package com.github.il4enkodev.househeating.presentation.ui.fragment.readings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.github.il4enkodev.househeating.presentation.di.qualifier.DateFormatter
import com.github.il4enkodev.househeating.presentation.di.qualifier.TimeFormatter
import com.github.il4enkodev.househeating.presentation.model.ReadingModel
import com.github.il4enkodev.househeating.presentation.ui.events.LiveEvent
import com.github.il4enkodev.househeating.presentation.ui.events.LiveEvent.NotificationStrategy.NOTIFY_FIRST
import com.github.il4enkodev.househeating.presentation.ui.events.ViewRequest
import com.github.il4enkodev.househeating.presentation.ui.events.ViewResult
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjuster
import javax.inject.Inject

@HiltViewModel
class ReadingsEditViewModel @Inject constructor(
        app: Application,
        @DateFormatter val dateFormatter: DateTimeFormatter,
        @TimeFormatter val timeFormatter: DateTimeFormatter,
        val input: ReadingInputHelper
): AndroidViewModel(app) {

    val readings = MediatorLiveData<ReadingModel>().apply {
        addSource(input.number) { value = value?.copy(value=it) }
    }

    fun initialize(model: ReadingModel) {
        Timber.i("Initializing with: $model")
        readings.value = model
        input.set(model.value)
    }

    // <--[Navigate to another view]-- View <--[request(editTime, editDate, etc)]-- ViewModel
    // --[result(timeUpdated, dateUpdated, etc)]--> View --> ViewModel.onViewResult()
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
        result.push(ViewResult.ReadingUpdated(readings.value))
    }

    private fun updateDateTime(adjuster: TemporalAdjuster?) {
        if (adjuster == null) return
        readings.value = readings.value?.let {
            it.copy(time=it.time.with(adjuster))
        }
    }

}