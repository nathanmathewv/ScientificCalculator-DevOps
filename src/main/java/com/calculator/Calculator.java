package com.calculator;

public class Calculator {

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }
    
    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }

    public double sqrt(double x) {
        if (x < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of negative number");
        }
        return Math.sqrt(x);
    }

    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    public long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial not defined for negative numbers");
        }
        
        if (n == 0 || n == 1) {
            return 1;
        }
        
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public double ln(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException("Logarithm undefined for non-positive numbers");
        }
        return Math.log(x);
    }

    public double log(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException("Logarithm undefined for non-positive numbers");
        }
        return Math.log10(x);
    }

    public double exp(double x) {
        return Math.exp(x);
    }
}
