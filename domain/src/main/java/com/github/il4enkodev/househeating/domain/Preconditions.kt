package com.github.il4enkodev.househeating.domain

fun <T> requireNotNull(instance: T?, name: String?): T {
    if (instance == null) throw NullPointerException(if (name != null) "$name is null" else "")
    return instance
}