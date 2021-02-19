package com.github.il4enkodev.househeating.domain.exception

open class IllegalEntityStateException(message: String?,
                                       cause: Throwable?
) : IllegalStateException(message, cause)