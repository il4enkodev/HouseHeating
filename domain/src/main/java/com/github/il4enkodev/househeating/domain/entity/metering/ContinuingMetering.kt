package com.github.il4enkodev.househeating.domain.entity.metering

import com.github.il4enkodev.househeating.domain.exception.MeteringNotCompletedYet
import java.time.Duration
import java.time.ZonedDateTime

class ContinuingMetering(id: String,
                         start: MeterReadings): AbstractMetering<Double>(id, start) {

    override val duration: Duration
        get()  = Duration.between(start.time,
                ZonedDateTime.now().withZoneSameInstant(start.time.zone))

    override val end = throw MeteringNotCompletedYet(this)
    override val usage = throw MeteringNotCompletedYet(this)


    override val isCompleted = false
}