package com.github.il4enkodev.househeating.domain.entity.metering

import java.time.ZonedDateTime

data class MeterReadings(val time: ZonedDateTime, val value: Double)