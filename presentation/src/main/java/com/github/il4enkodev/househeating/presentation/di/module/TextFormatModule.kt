package com.github.il4enkodev.househeating.presentation.di.module

import com.github.il4enkodev.househeating.presentation.di.qualifier.DateFormatter
import com.github.il4enkodev.househeating.presentation.di.qualifier.ReadingFormat
import com.github.il4enkodev.househeating.presentation.di.qualifier.TimeFormatter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Module
@InstallIn(SingletonComponent::class)
object TextFormatModule {

    @Provides
    @ReadingFormat
    fun provideReadingFormat(): String {
        // TODO retrieve format from SharedPreference
        return "00000.000"
    }

    @Provides
    @TimeFormatter
    fun provideTimeFormatter(): DateTimeFormatter {
        return DateTimeFormatter.ofPattern("HH:mm")
    }

    @Provides
    @DateFormatter
    fun provideDateFormatter(): DateTimeFormatter {
        return DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
    }

}