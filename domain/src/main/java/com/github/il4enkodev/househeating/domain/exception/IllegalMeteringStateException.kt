package com.github.il4enkodev.househeating.domain.exception

import com.github.il4enkodev.househeating.domain.entity.metering.Metering

open class IllegalMeteringStateException(val metering: Metering<*>,
                                         message: String?,
                                         cause: Throwable?
) : IllegalEntityStateException(message, cause)