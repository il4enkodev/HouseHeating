package com.github.il4enkodev.househeating.domain.entity.metering

import java.time.Duration

class CompletedMetering internal constructor(id: String,
                                             start: MeterReadings,
                                             override val end: MeterReadings
) : AbstractMetering<Double>(id, start) {

    override val isCompleted = true
    override val usage: Double by lazy { end.value - start.value }
    override val duration: Duration by lazy { Duration.between(start.time, end.time) }
}