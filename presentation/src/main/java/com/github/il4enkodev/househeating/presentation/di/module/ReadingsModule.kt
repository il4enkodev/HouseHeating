package com.github.il4enkodev.househeating.presentation.di.module

import android.text.InputFilter
import com.github.il4enkodev.househeating.presentation.di.qualifier.DateFormatter
import com.github.il4enkodev.househeating.presentation.di.qualifier.ReadingFilters
import com.github.il4enkodev.househeating.presentation.di.qualifier.ReadingPattern
import com.github.il4enkodev.househeating.presentation.di.qualifier.TimeFormatter
import com.github.il4enkodev.househeating.presentation.ui.text.RegexInputFilter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.regex.Pattern

@Module
@InstallIn(ActivityRetainedComponent::class)
object ReadingsModule {

    @Provides
    @ReadingPattern
    fun providePattern(): Pattern {
        return Pattern.compile("(\\d{5})[.,](\\d{3})")
    }

    @Provides
    @ReadingFilters
    fun provideInputFilters(@ReadingPattern pattern: Pattern): Array<InputFilter> {
        return arrayOf(RegexInputFilter(pattern))
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