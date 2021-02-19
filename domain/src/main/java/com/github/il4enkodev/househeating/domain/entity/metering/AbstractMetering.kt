package com.github.il4enkodev.househeating.domain.entity.metering

abstract class AbstractMetering<U>(override val id: String,
                                            override val start: MeterReadings
) : Metering<U> {

    override val isStarted = true

    override val isContinuing: Boolean
        get() = !isCompleted
}