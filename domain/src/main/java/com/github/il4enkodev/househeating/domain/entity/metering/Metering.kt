package com.github.il4enkodev.househeating.domain.entity.metering

import com.github.il4enkodev.househeating.domain.entity.Entity
import com.github.il4enkodev.househeating.domain.exception.MeteringNotStartedYet
import java.time.Duration
import javax.annotation.concurrent.Immutable

@Immutable
interface Metering<U> : Entity<String?> {

    val start: MeterReadings
    val end: MeterReadings
    val usage: U
    val duration: Duration
    val isStarted: Boolean
    val isCompleted: Boolean
    val isContinuing: Boolean

    companion object {

        fun <U> notStarted(): Metering<U> {
            @Suppress("UNCHECKED_CAST")
            return NOT_STARTED as Metering<U>
        }

        private val NOT_STARTED: Metering<*> = object : Metering<Any?> {
            override val id: String? = null
            override val start = throw MeteringNotStartedYet(this)
            override val end = throw MeteringNotStartedYet(this)
            override val usage = throw MeteringNotStartedYet(this)
            override val duration = Duration.ZERO

            override val isStarted = false
            override val isCompleted = false
            override val isContinuing = false

            override fun toString(): String {
                return "Metering <Not Started>"
            }
        }
    }
}