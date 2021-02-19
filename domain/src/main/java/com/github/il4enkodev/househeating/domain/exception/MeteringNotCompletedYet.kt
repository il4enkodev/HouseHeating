package com.github.il4enkodev.househeating.domain.exception

import com.github.il4enkodev.househeating.domain.entity.metering.Metering

class MeteringNotCompletedYet(metering: Metering<*>,
                              message: String? = null,
                              cause: Throwable? = null
) : IllegalMeteringStateException(metering, message, cause)