package com.danielblanco.cleancode;

public class Calculator {

    public Double multiply(Double a, Double b) {
        return a * b;
    }

    public Double divide(Double a, Double b) {
        if (b == 0)
            throw new DivisionByZeroException("Second parameter was 0. This is not allowed!");

        return a / b;
    }

    public Double abs(Double number) {
        if (number >= 0)
            return number;

        return -number;
    }
}
