package com.danielblanco.cleancode;

public class DivisionByZeroException extends RuntimeException {

    public DivisionByZeroException(String message) {
        super(message);
    }
}
