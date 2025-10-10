import math


class Calculator:
    def add(self, a, b):
        return a + b
    
    def subtract(self, a, b):
        return a - b
    
    def multiply(self, a, b):
        return a * b
    
    def divide(self, a, b):
        if b == 0:
            raise ValueError("Cannot divide by zero")
        return a / b
    
    def power(self, base, exponent):
        return math.pow(base, exponent)
    
    def sqrt(self, x):
        if x < 0:
            raise ValueError("Cannot calculate square root of negative number")
        return math.sqrt(x)
    
    def factorial(self, n):
        if n < 0:
            raise ValueError("Factorial not defined for negative numbers")
        if not float(n).is_integer():
            raise ValueError("Factorial only defined for integers")
        return math.factorial(int(n))
    
    def log(self, x):
        if x <= 0:
            raise ValueError("Logarithm undefined for non-positive numbers")
        return math.log10(x)
    
    def ln(self, x):
        if x <= 0:
            raise ValueError("Logarithm undefined for non-positive numbers")
        return math.log(x)
    
    def exp(self, x):
        return math.exp(x)