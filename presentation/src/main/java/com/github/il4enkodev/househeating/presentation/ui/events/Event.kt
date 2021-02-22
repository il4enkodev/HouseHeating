package com.github.il4enkodev.househeating.presentation.ui.events

open class Event<out T>(val content: T, open val sticky: Boolean = false)