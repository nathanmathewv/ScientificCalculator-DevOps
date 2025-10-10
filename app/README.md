# Scientific Calculator - Python Implementation

A scientific calculator built with Python and Tkinter. Supports basic arithmetic operations and essential scientific functions.

## Features

### Basic Operations
- Addition, Subtraction, Multiplication, Division
- Decimal point support
- Sign change (positive/negative)
- Backspace and clear functions

### Scientific Functions
- **Square Root (√)**: Calculate square root of a number
- **Power (xʸ)**: Raise a number to any power
- **Factorial (n!)**: Calculate factorial of an integer
- **Natural Logarithm (ln)**: Calculate natural logarithm (base e)

## Prerequisites

- Python 3.6 or higher
- Tkinter (for GUI)

## Installation

### 1. Install Tkinter (if not available)

**macOS:**
```bash
brew install python-tk@3.13  # Replace 3.13 with your Python version
```

**Ubuntu/Debian:**
```bash
sudo apt-get install python3-tk
```

**Fedora:**
```bash
sudo dnf install python3-tkinter
```

**Windows:**
Tkinter is included with the official Python installer.

### 2. Install Testing Dependencies (Optional)

```bash
pip install -r requirements.txt
```

For development tools:
```bash
pip install -r requirements-dev.txt
```

## Running the Calculator

### Basic Usage
```bash
python3 calculator_gui.py
```

Or from the project root:
```bash
python3 app/calculator_gui.py
```

## Testing

### Run Unit Tests
```bash
python3 test_calculator.py
```

### Run with pytest (if installed)
```bash
pytest test_calculator.py -v
```

### Run with coverage
```bash
pytest test_calculator.py --cov=calculator --cov-report=html
```

## Project Structure

```
app/
├── calculator.py         # Core calculator logic (148 lines)
├── calculator_gui.py     # Tkinter GUI (280 lines)
├── test_calculator.py    # Unit tests (280+ lines)
├── requirements.txt      # Testing dependencies
└── requirements-dev.txt  # Development dependencies
```

## How to Use

### Basic Calculations
1. Click number buttons (0-9) and decimal point (.)
2. Click operation buttons (+, -, ×, ÷)
3. Press = to calculate result

### Scientific Functions

**Square Root (√)**
- Enter a number (e.g., 144)
- Click √
- Result: 12

**Power (xʸ)**
- Enter base (e.g., 2)
- Click xʸ
- Enter exponent (e.g., 8)
- Click =
- Result: 256

**Factorial (n!)**
- Enter an integer (e.g., 5)
- Click n!
- Result: 120

**Natural Logarithm (ln)**
- Enter a number (e.g., 10)
- Click ln
- Result: 2.302585093

### Clear Functions
- **C**: Clear all (reset calculator)
- **CE**: Clear current entry
- **←**: Backspace (remove last digit)

### Sign Change
- **±**: Toggle between positive and negative

## Button Color Scheme

- **Red**: Clear/Delete operations (C, CE, ←)
- **Cyan**: Basic arithmetic operations (+, -, ×, ÷, =)
- **Yellow**: Scientific functions (√, xʸ, n!, ln)
- **Gray**: Numbers and decimal point

## Error Handling

The calculator handles common error cases:
- Division by zero
- Square root of negative numbers
- Logarithm of non-positive numbers
- Factorial of negative or non-integer numbers

## Testing Details

The test suite (`test_calculator.py`) includes comprehensive tests:

### Square Root Tests
- Positive integers and decimals
- Zero
- Negative numbers (error cases)
- Large numbers
- Very small positive numbers

### Factorial Tests
- Small positive integers (0, 1, 5, 10)
- Specific values (3!, 4!, 6!, 7!)
- Negative numbers (error cases)
- Zero (edge case: 0! = 1)
- Larger values (12!, 15!)

### Natural Logarithm Tests
- Positive numbers (ln(1), ln(e), ln(e²))
- Known values (ln(2), ln(10), ln(100))
- Decimal numbers
- Zero and negative numbers (error cases)
- Very large numbers

### Power Function Tests
- Positive and negative exponents
- Zero exponent (x⁰ = 1)
- Fractional exponents (roots)
- Negative base
- Zero base
- Large exponents
- Identity cases

### Basic Operations Tests
- Addition, subtraction, multiplication, division
- Division by zero (error case)
- Logarithm base 10
- Exponential function (eˣ)

**Total: ~40 test cases**

## Dependencies

### Runtime
- Python 3.6+
- tkinter (standard library, may need system install)
- math (standard library)

### Testing (Optional)
- pytest >= 7.0.0
- pytest-cov >= 4.0.0
- unittest-xml-reporting >= 3.2.0

### Development (Optional)
- pylint >= 3.0.0
- flake8 >= 6.0.0
- black >= 23.0.0
- mypy >= 1.0.0

## Code Structure

### calculator.py
Core calculator class with methods:
- Basic operations: `add()`, `subtract()`, `multiply()`, `divide()`
- Scientific functions: `sqrt()`, `power()`, `factorial()`, `ln()`
- Additional functions: `log()`, `exp()`

### calculator_gui.py
GUI implementation using Tkinter:
- `ScientificCalculatorGUI` class
- Button layout and event handling
- Display formatting and error messages
- Color-coded interface

### test_calculator.py
Comprehensive unit tests using unittest:
- Test classes: `TestCalculator`, `TestCalculatorEdgeCases`
- Parameterized tests
- Edge case testing
- Error condition testing

## Troubleshooting

### Tkinter Not Found
```
ModuleNotFoundError: No module named '_tkinter'
```
**Solution**: Install python-tk using the commands in the Installation section.

### Display Issues
If the GUI doesn't display correctly, try:
```bash
export DISPLAY=:0  # On Linux
```

### Python Version
Check your Python version:
```bash
python3 --version
```
Make sure it's 3.6 or higher.

## Contributing

When making changes:
1. Update tests for new functionality
2. Run tests to ensure nothing breaks
3. Follow PEP 8 style guidelines
4. Update documentation

## License

This project is part of an academic assignment.

## Author

Nathan Mathew V
