import unittest
import math
from calculator import Calculator


class TestCalculator(unittest.TestCase):
    
    def setUp(self):
        self.calc = Calculator()
    
    def test_sqrt_positive_integer(self):
        self.assertEqual(self.calc.sqrt(4), 2.0)
        self.assertEqual(self.calc.sqrt(9), 3.0)
        self.assertEqual(self.calc.sqrt(16), 4.0)
        self.assertEqual(self.calc.sqrt(144), 12.0)
    
    def test_sqrt_positive_decimal(self):
        self.assertAlmostEqual(self.calc.sqrt(2), 1.4142135623730951, places=10)
        self.assertAlmostEqual(self.calc.sqrt(0.25), 0.5, places=10)
        self.assertAlmostEqual(self.calc.sqrt(2.25), 1.5, places=10)
    
    def test_sqrt_zero(self):
        self.assertEqual(self.calc.sqrt(0), 0.0)
    
    def test_sqrt_negative_raises_error(self):
        with self.assertRaises(ValueError) as context:
            self.calc.sqrt(-4)
        self.assertIn("Cannot calculate square root of negative number", str(context.exception))
    
    def test_sqrt_large_number(self):
        self.assertEqual(self.calc.sqrt(10000), 100.0)
        self.assertAlmostEqual(self.calc.sqrt(1000000), 1000.0, places=10)
    
    
    def test_factorial_small_positive(self):
        self.assertEqual(self.calc.factorial(0), 1)
        self.assertEqual(self.calc.factorial(1), 1)
        self.assertEqual(self.calc.factorial(5), 120)
        self.assertEqual(self.calc.factorial(10), 3628800)
    
    def test_factorial_specific_values(self):
        self.assertEqual(self.calc.factorial(3), 6)
        self.assertEqual(self.calc.factorial(4), 24)
        self.assertEqual(self.calc.factorial(6), 720)
        self.assertEqual(self.calc.factorial(7), 5040)
    
    def test_factorial_negative_raises_error(self):
        with self.assertRaises(ValueError) as context:
            self.calc.factorial(-1)
        self.assertIn("Factorial not defined for negative numbers", str(context.exception))
        
        with self.assertRaises(ValueError):
            self.calc.factorial(-5)
    
    def test_factorial_non_integer_raises_error(self):
        with self.assertRaises(ValueError) as context:
            self.calc.factorial(3.5)
        self.assertIn("Factorial only defined for integers", str(context.exception))
        
        with self.assertRaises(ValueError):
            self.calc.factorial(2.1)
    
    def test_factorial_zero(self):
        self.assertEqual(self.calc.factorial(0), 1)
    
    
    def test_ln_positive_numbers(self):
        self.assertAlmostEqual(self.calc.ln(1), 0.0, places=10)
        self.assertAlmostEqual(self.calc.ln(math.e), 1.0, places=10)
        self.assertAlmostEqual(self.calc.ln(math.e**2), 2.0, places=10)
    
    def test_ln_specific_values(self):
        self.assertAlmostEqual(self.calc.ln(2), 0.6931471805599453, places=10)
        self.assertAlmostEqual(self.calc.ln(10), 2.302585092994046, places=10)
        self.assertAlmostEqual(self.calc.ln(100), 4.605170185988092, places=10)
    
    def test_ln_decimal_numbers(self):
        self.assertAlmostEqual(self.calc.ln(0.5), -0.6931471805599453, places=10)
        self.assertAlmostEqual(self.calc.ln(1.5), 0.4054651081081644, places=10)
    
    def test_ln_zero_raises_error(self):
        with self.assertRaises(ValueError) as context:
            self.calc.ln(0)
        self.assertIn("Logarithm undefined for non-positive numbers", str(context.exception))
    
    def test_ln_negative_raises_error(self):
        with self.assertRaises(ValueError) as context:
            self.calc.ln(-1)
        self.assertIn("Logarithm undefined for non-positive numbers", str(context.exception))
        
        with self.assertRaises(ValueError):
            self.calc.ln(-10)
    
    
    def test_power_positive_exponents(self):
        self.assertEqual(self.calc.power(2, 3), 8.0)
        self.assertEqual(self.calc.power(3, 2), 9.0)
        self.assertEqual(self.calc.power(5, 3), 125.0)
        self.assertEqual(self.calc.power(10, 2), 100.0)
    
    def test_power_zero_exponent(self):
        self.assertEqual(self.calc.power(5, 0), 1.0)
        self.assertEqual(self.calc.power(100, 0), 1.0)
        self.assertEqual(self.calc.power(0.5, 0), 1.0)
    
    def test_power_negative_exponents(self):
        self.assertAlmostEqual(self.calc.power(2, -1), 0.5, places=10)
        self.assertAlmostEqual(self.calc.power(4, -2), 0.0625, places=10)
        self.assertAlmostEqual(self.calc.power(10, -1), 0.1, places=10)
    
    def test_power_fractional_exponents(self):
        self.assertAlmostEqual(self.calc.power(4, 0.5), 2.0, places=10)  # square root
        self.assertAlmostEqual(self.calc.power(8, 1/3), 2.0, places=10)  # cube root
        self.assertAlmostEqual(self.calc.power(27, 1/3), 3.0, places=10)
    
    def test_power_negative_base(self):
        self.assertEqual(self.calc.power(-2, 2), 4.0)
        self.assertEqual(self.calc.power(-2, 3), -8.0)
        self.assertEqual(self.calc.power(-3, 2), 9.0)
    
    def test_power_zero_base(self):
        self.assertEqual(self.calc.power(0, 5), 0.0)
        self.assertEqual(self.calc.power(0, 100), 0.0)
    
    def test_power_decimal_base(self):
        self.assertAlmostEqual(self.calc.power(1.5, 2), 2.25, places=10)
        self.assertAlmostEqual(self.calc.power(2.5, 3), 15.625, places=10)
    
    def test_power_large_exponents(self):
        self.assertEqual(self.calc.power(2, 10), 1024.0)
        self.assertEqual(self.calc.power(10, 6), 1000000.0)
    
    
    def test_add(self):
        self.assertEqual(self.calc.add(2, 3), 5)
        self.assertEqual(self.calc.add(-1, 1), 0)
        self.assertEqual(self.calc.add(0.1, 0.2), 0.30000000000000004)
    
    def test_subtract(self):
        self.assertEqual(self.calc.subtract(5, 3), 2)
        self.assertEqual(self.calc.subtract(0, 5), -5)
        self.assertEqual(self.calc.subtract(-5, -3), -2)
    
    def test_multiply(self):
        self.assertEqual(self.calc.multiply(3, 4), 12)
        self.assertEqual(self.calc.multiply(-2, 5), -10)
        self.assertEqual(self.calc.multiply(0, 100), 0)
    
    def test_divide(self):
        self.assertEqual(self.calc.divide(10, 2), 5)
        self.assertAlmostEqual(self.calc.divide(7, 3), 2.3333333333333335, places=10)
        self.assertEqual(self.calc.divide(-10, 2), -5)
    
    def test_divide_by_zero_raises_error(self):
        with self.assertRaises(ValueError) as context:
            self.calc.divide(5, 0)
        self.assertIn("Cannot divide by zero", str(context.exception))
    
    def test_log(self):
        self.assertAlmostEqual(self.calc.log(10), 1.0, places=10)
        self.assertAlmostEqual(self.calc.log(100), 2.0, places=10)
        self.assertAlmostEqual(self.calc.log(1), 0.0, places=10)
    
    def test_exp(self):
        self.assertAlmostEqual(self.calc.exp(0), 1.0, places=10)
        self.assertAlmostEqual(self.calc.exp(1), math.e, places=10)
        self.assertAlmostEqual(self.calc.exp(2), math.e**2, places=10)


class TestCalculatorEdgeCases(unittest.TestCase):
    
    def setUp(self):
        """Set up test calculator instance"""
        self.calc = Calculator()
    
    def test_sqrt_very_small_positive(self):
        result = self.calc.sqrt(0.0001)
        self.assertAlmostEqual(result, 0.01, places=10)
    
    def test_factorial_larger_values(self):
        self.assertEqual(self.calc.factorial(12), 479001600)
        self.assertEqual(self.calc.factorial(15), 1307674368000)
    
    def test_ln_very_large_number(self):
        result = self.calc.ln(1000000)
        self.assertAlmostEqual(result, 13.815510557964274, places=10)
    
    def test_power_identity_cases(self):
        self.assertEqual(self.calc.power(1, 1000), 1.0)
        self.assertEqual(self.calc.power(1000, 1), 1000.0)


if __name__ == '__main__':
    # Run tests with verbose output
    unittest.main(verbosity=2)
