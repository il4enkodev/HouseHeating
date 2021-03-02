package com.github.il4enkodev.househeating.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.il4enkodev.househeating.presentation.ui.events.ClickEvent
import com.github.il4enkodev.househeating.presentation.ui.events.LiveEvent
import com.github.il4enkodev.househeating.presentation.ui.events.LiveEvent.NotificationStrategy
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FabViewModel @Inject constructor() : ViewModel() {

    /** FAB is invisible until no one has subscribed to the click event */
    fun visibility(): LiveData<Boolean> = visibility
    private val visibility = MutableLiveData<Boolean>()

    fun clicks(): LiveData<ClickEvent> = clicks
    private val clicks = LiveEvent<ClickEvent>(NotificationStrategy.NOTIFY_LAST) {
        visibility.value = it
    }

    fun clicked() {
        clicks.push(ClickEvent)
    }
}