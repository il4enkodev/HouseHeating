package com.github.il4enkodev.househeating.domain.exception;

public class IllegalEntityStateException extends IllegalStateException {

    public IllegalEntityStateException() {
    }

    public IllegalEntityStateException(String s) {
        super(s);
    }

    public IllegalEntityStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalEntityStateException(Throwable cause) {
        super(cause);
    }
}
