package com.github.il4enkodev.househeating.presentation.di.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ReadingFormat

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DateFormatter

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TimeFormatter