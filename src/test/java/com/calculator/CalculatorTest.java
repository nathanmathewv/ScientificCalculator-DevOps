package com.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests for Scientific Calculator
 * Tests the core mathematical operations, with focus on:
 * - Square root
 * - Factorial
 * - Natural logarithm
 * - Power function
 */
@DisplayName("Calculator Tests")
class CalculatorTest {
    
    private Calculator calculator;
    
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }
    
    // ===== SQUARE ROOT TESTS =====
    
    @Test
    @DisplayName("Test square root of positive integers")
    void testSqrtPositiveIntegers() {
        assertEquals(2.0, calculator.sqrt(4), 0.0001);
        assertEquals(3.0, calculator.sqrt(9), 0.0001);
        assertEquals(4.0, calculator.sqrt(16), 0.0001);
        assertEquals(12.0, calculator.sqrt(144), 0.0001);
    }
    
    @Test
    @DisplayName("Test square root of positive decimals")
    void testSqrtPositiveDecimals() {
        assertEquals(1.4142135623730951, calculator.sqrt(2), 0.0000000001);
        assertEquals(0.5, calculator.sqrt(0.25), 0.0001);
        assertEquals(1.5, calculator.sqrt(2.25), 0.0001);
    }
    
    @Test
    @DisplayName("Test square root of zero")
    void testSqrtZero() {
        assertEquals(0.0, calculator.sqrt(0), 0.0001);
    }
    
    @Test
    @DisplayName("Test square root of negative number throws exception")
    void testSqrtNegativeThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.sqrt(-4);
        });
        assertTrue(exception.getMessage().contains("Cannot calculate square root of negative number"));
    }
    
    @Test
    @DisplayName("Test square root of large numbers")
    void testSqrtLargeNumbers() {
        assertEquals(100.0, calculator.sqrt(10000), 0.0001);
        assertEquals(1000.0, calculator.sqrt(1000000), 0.0001);
    }
    
    // ===== FACTORIAL TESTS =====
    
    @ParameterizedTest
    @CsvSource({
        "0, 1",
        "1, 1",
        "5, 120",
        "10, 3628800"
    })
    @DisplayName("Test factorial of small positive integers")
    void testFactorialSmallPositive(int input, long expected) {
        assertEquals(expected, calculator.factorial(input));
    }
    
    @Test
    @DisplayName("Test factorial specific values")
    void testFactorialSpecificValues() {
        assertEquals(6, calculator.factorial(3));
        assertEquals(24, calculator.factorial(4));
        assertEquals(720, calculator.factorial(6));
        assertEquals(5040, calculator.factorial(7));
    }
    
    @Test
    @DisplayName("Test factorial of negative number throws exception")
    void testFactorialNegativeThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.factorial(-1);
        });
        assertTrue(exception.getMessage().contains("Factorial not defined for negative numbers"));
    }
    
    @Test
    @DisplayName("Test factorial of zero")
    void testFactorialZero() {
        assertEquals(1, calculator.factorial(0));
    }
    
    @Test
    @DisplayName("Test factorial of larger values")
    void testFactorialLargerValues() {
        assertEquals(479001600L, calculator.factorial(12));
        assertEquals(6227020800L, calculator.factorial(13));
    }
    
    // ===== NATURAL LOGARITHM TESTS =====
    
    @Test
    @DisplayName("Test natural logarithm of positive numbers")
    void testLnPositiveNumbers() {
        assertEquals(0.0, calculator.ln(1), 0.0000000001);
        assertEquals(1.0, calculator.ln(Math.E), 0.0000000001);
        assertEquals(2.0, calculator.ln(Math.E * Math.E), 0.0000000001);
    }
    
    @Test
    @DisplayName("Test ln with specific known values")
    void testLnSpecificValues() {
        assertEquals(0.6931471805599453, calculator.ln(2), 0.0000000001);
        assertEquals(2.302585092994046, calculator.ln(10), 0.0000000001);
        assertEquals(4.605170185988092, calculator.ln(100), 0.0000000001);
    }
    
    @Test
    @DisplayName("Test natural logarithm of decimal numbers")
    void testLnDecimalNumbers() {
        assertEquals(-0.6931471805599453, calculator.ln(0.5), 0.0000000001);
        assertEquals(0.4054651081081644, calculator.ln(1.5), 0.0000000001);
    }
    
    @Test
    @DisplayName("Test ln of zero throws exception")
    void testLnZeroThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.ln(0);
        });
        assertTrue(exception.getMessage().contains("Logarithm undefined for non-positive numbers"));
    }
    
    @Test
    @DisplayName("Test ln of negative number throws exception")
    void testLnNegativeThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.ln(-1);
        });
        assertTrue(exception.getMessage().contains("Logarithm undefined for non-positive numbers"));
    }
    
    @Test
    @DisplayName("Test ln of very large number")
    void testLnVeryLargeNumber() {
        assertEquals(13.815510557964274, calculator.ln(1000000), 0.0000000001);
    }
    
    // ===== POWER FUNCTION TESTS =====
    
    @ParameterizedTest
    @CsvSource({
        "2, 3, 8",
        "3, 2, 9",
        "5, 3, 125",
        "10, 2, 100"
    })
    @DisplayName("Test power function with positive exponents")
    void testPowerPositiveExponents(double base, double exponent, double expected) {
        assertEquals(expected, calculator.power(base, exponent), 0.0001);
    }
    
    @ParameterizedTest
    @ValueSource(doubles = {5, 100, 0.5})
    @DisplayName("Test power function with zero exponent")
    void testPowerZeroExponent(double base) {
        assertEquals(1.0, calculator.power(base, 0), 0.0001);
    }
    
    @Test
    @DisplayName("Test power function with negative exponents")
    void testPowerNegativeExponents() {
        assertEquals(0.5, calculator.power(2, -1), 0.0001);
        assertEquals(0.0625, calculator.power(4, -2), 0.0001);
        assertEquals(0.1, calculator.power(10, -1), 0.0001);
    }
    
    @Test
    @DisplayName("Test power function with fractional exponents")
    void testPowerFractionalExponents() {
        assertEquals(2.0, calculator.power(4, 0.5), 0.0001); // square root
        assertEquals(2.0, calculator.power(8, 1.0/3.0), 0.0001); // cube root
        assertEquals(3.0, calculator.power(27, 1.0/3.0), 0.0001);
    }
    
    @Test
    @DisplayName("Test power function with negative base")
    void testPowerNegativeBase() {
        assertEquals(4.0, calculator.power(-2, 2), 0.0001);
        assertEquals(-8.0, calculator.power(-2, 3), 0.0001);
        assertEquals(9.0, calculator.power(-3, 2), 0.0001);
    }
    
    @Test
    @DisplayName("Test power function with zero base")
    void testPowerZeroBase() {
        assertEquals(0.0, calculator.power(0, 5), 0.0001);
        assertEquals(0.0, calculator.power(0, 100), 0.0001);
    }
    
    @Test
    @DisplayName("Test power function with decimal base")
    void testPowerDecimalBase() {
        assertEquals(2.25, calculator.power(1.5, 2), 0.0001);
        assertEquals(15.625, calculator.power(2.5, 3), 0.0001);
    }
    
    @Test
    @DisplayName("Test power function with large exponents")
    void testPowerLargeExponents() {
        assertEquals(1024.0, calculator.power(2, 10), 0.0001);
        assertEquals(1000000.0, calculator.power(10, 6), 0.0001);
    }
    
    @Test
    @DisplayName("Test power identity cases")
    void testPowerIdentityCases() {
        assertEquals(1.0, calculator.power(1, 1000), 0.0001);
        assertEquals(1000.0, calculator.power(1000, 1), 0.0001);
    }
    
    // ===== BASIC ARITHMETIC TESTS =====
    
    @Test
    @DisplayName("Test addition")
    void testAdd() {
        assertEquals(5.0, calculator.add(2, 3), 0.0001);
        assertEquals(0.0, calculator.add(-1, 1), 0.0001);
        assertEquals(0.3, calculator.add(0.1, 0.2), 0.0001);
    }
    
    @Test
    @DisplayName("Test subtraction")
    void testSubtract() {
        assertEquals(2.0, calculator.subtract(5, 3), 0.0001);
        assertEquals(-5.0, calculator.subtract(0, 5), 0.0001);
        assertEquals(-2.0, calculator.subtract(-5, -3), 0.0001);
    }
    
    @Test
    @DisplayName("Test multiplication")
    void testMultiply() {
        assertEquals(12.0, calculator.multiply(3, 4), 0.0001);
        assertEquals(-10.0, calculator.multiply(-2, 5), 0.0001);
        assertEquals(0.0, calculator.multiply(0, 100), 0.0001);
    }
    
    @Test
    @DisplayName("Test division")
    void testDivide() {
        assertEquals(5.0, calculator.divide(10, 2), 0.0001);
        assertEquals(2.3333333333333335, calculator.divide(7, 3), 0.0001);
        assertEquals(-5.0, calculator.divide(-10, 2), 0.0001);
    }
    
    @Test
    @DisplayName("Test division by zero throws exception")
    void testDivideByZeroThrowsException() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculator.divide(5, 0);
        });
        assertTrue(exception.getMessage().contains("Cannot divide by zero"));
    }
    
    @Test
    @DisplayName("Test logarithm base 10")
    void testLog() {
        assertEquals(1.0, calculator.log(10), 0.0000000001);
        assertEquals(2.0, calculator.log(100), 0.0000000001);
        assertEquals(0.0, calculator.log(1), 0.0000000001);
    }
    
    @Test
    @DisplayName("Test exponential function")
    void testExp() {
        assertEquals(1.0, calculator.exp(0), 0.0000000001);
        assertEquals(Math.E, calculator.exp(1), 0.0000000001);
        assertEquals(Math.E * Math.E, calculator.exp(2), 0.0000000001);
    }
}
