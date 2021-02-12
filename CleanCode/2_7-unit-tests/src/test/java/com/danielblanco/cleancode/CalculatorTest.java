package com.danielblanco.cleancode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testMultiply() {
        assertEquals(4.6, calculator.multiply(2.0, 2.3));
    }

    @Test
    public void testValidDivision() {
        assertEquals(3, calculator.divide(6.0, 2.0));
    }

    @Test
    public void testInvalidDivision() {
        assertThrows(DivisionByZeroException.class, () -> {
            calculator.divide(5.0, 0.0);
        });
    }

    @Test
    public void testAbsPositiveNumber() {
        assertEquals(3.2, calculator.abs(3.2));
    }

    @Test
    public void testAbsNegativeNumber() {
        assertEquals(3.2, calculator.abs(-3.2));
    }
}
