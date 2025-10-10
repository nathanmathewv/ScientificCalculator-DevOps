# Scientific Calculator

A scientific calculator with implementations in both **Python** and **Java**. Features a graphical user interface and comprehensive test coverage for essential mathematical operations.

## Implementations

| Language | Directory | Framework | Build Tool | Tests |
|----------|-----------|-----------|------------|-------|
| **Python** | `app/` | Tkinter | pip | unittest |
| **Java** | `java-calculator/` | Swing | Maven | JUnit 5 |

📖 **Detailed Documentation:**
- [Python Implementation README](app/README.md)
- [Java Implementation README](java-calculator/README.md)
- [Python vs Java Comparison](COMPARISON.md)

## Core Features

Both implementations provide identical functionality:

### ➕ Basic Operations
- Addition, Subtraction, Multiplication, Division
- Decimal point support
- Sign change (positive/negative)
- Clear and backspace functions

### 🔬 Scientific Functions
- **√ (Square Root)**: Calculate square root of a number
- **xʸ (Power)**: Raise a number to any power
- **n! (Factorial)**: Calculate factorial of an integer
- **ln (Natural Log)**: Calculate natural logarithm (base e)

## Quick Start

### Clone Repository
```bash
git clone https://github.com/nathanmathewv/ScientificCalculator-DevOps.git
cd ScientificCalculator-DevOps
```

### Choose Your Implementation

**Python** (Quick to run, no compilation)
```bash
cd app
python3 calculator_gui.py
```
[→ Python Setup Instructions](app/README.md)

**Java** (Professional build, standalone JAR)
```bash
cd java-calculator
mvn clean package
java -jar target/scientific-calculator-1.0.0.jar
```
[→ Java Setup Instructions](java-calculator/README.md)

## Usage Guide

### Basic Operations
1. Enter numbers using number buttons (0-9)
2. Click operation buttons (+, -, ×, ÷)
3. Press = to calculate

### Scientific Functions

| Function | Example | Result |
|----------|---------|--------|
| √ (Square Root) | 144 → √ | 12 |
| xʸ (Power) | 2 → xʸ → 8 → = | 256 |
| n! (Factorial) | 5 → n! | 120 |
| ln (Natural Log) | 10 → ln | 2.302585093 |

### Button Controls
- **C** - Clear all
- **CE** - Clear entry
- **←** - Backspace
- **±** - Change sign

## Project Structure

```
ScientificCalculator-DevOps/
├── README.md
├── app/                              # Python implementation
│   ├── calculator.py                 # Core calculator logic
│   ├── calculator_gui.py             # Tkinter GUI
│   ├── test_calculator.py            # Unit tests
│   ├── requirements.txt              # Python dependencies
│   └── requirements-dev.txt          # Development dependencies
├── java-calculator/                  # Java implementation
│   ├── pom.xml                       # Maven configuration
│   ├── README.md                     # Java-specific documentation
│   ├── src/
│   │   ├── main/java/com/calculator/
│   │   │   ├── Calculator.java       # Core calculator logic
│   │   │   └── CalculatorGUI.java    # Swing GUI
│   │   └── test/java/com/calculator/
│   │       └── CalculatorTest.java   # JUnit 5 tests
│   └── target/                       # Maven build output
```

## User Interface

### Button Layout
```
┌─────────────────────────────┐
│         Display             │
├──────┬──────┬──────┬────────┤
│  √   │ xʸ   │  n!  │   ln   │ ← Scientific
├──────┼──────┼──────┼────────┤
│  C   │  CE  │  ←   │   ÷    │ ← Clear/Ops
├──────┼──────┼──────┼────────┤
│  7   │  8   │  9   │   ×    │
├──────┼──────┼──────┼────────┤
│  4   │  5   │  6   │   -    │
├──────┼──────┼──────┼────────┤
│  1   │  2   │  3   │   +    │
├──────┼──────┼──────┼────────┤
│  ±   │  0   │  .   │   =    │
└──────┴──────┴──────┴────────┘
```

### Color Coding
- 🟥 **Red** - Clear/Delete (C, CE, ←)
- 🟦 **Cyan** - Arithmetic (+, -, ×, ÷, =)
- 🟨 **Yellow** - Scientific (√, xʸ, n!, ln)
- ⬜ **Gray** - Numbers (0-9, ., ±)

## Implementation Comparison

| Aspect | Python | Java |
|--------|--------|------|
| **Setup Time** | Instant | Requires compilation |
| **Runtime** | Needs Python | Standalone JAR |
| **GUI Framework** | Tkinter | Swing |
| **Build Tool** | None | Maven |
| **Package Size** | Small | Medium |
| **Type Safety** | Dynamic | Static |
| **Testing** | unittest | JUnit 5 |
| **Coverage Tool** | pytest-cov | JaCoCo |
| **CI/CD Ready** | ✓ | ✓✓ |
| **Best For** | Rapid dev | Production |

[→ Detailed Comparison](COMPARISON.md)

## Error Handling

Both implementations handle common error cases gracefully:

| Error Condition | Behavior |
|----------------|----------|
| Division by zero | Returns "Error" |
| Square root of negative | Returns "Error" |
| Logarithm of ≤ 0 | Returns "Error" |
| Factorial of negative | Returns "Error" |
| Factorial of non-integer | Returns "Error" |

## Architecture

Both implementations follow the same architectural pattern:

### Core Components

**Calculator Module** (calculator.py / Calculator.java)
- Stateless mathematical operations
- Input validation and error handling
- IEEE 754 floating-point arithmetic

**GUI Module** (calculator_gui.py / CalculatorGUI.java)
- 4×7 button grid layout
- Real-time display updates
- Event-driven architecture
- Color-coded button categories

**Test Module** (test_calculator.py / CalculatorTest.java)
- 40+ comprehensive test cases
- Edge case validation
- Parameterized testing (Java)
- Complete coverage of core functions

### Design Patterns
- **Separation of Concerns**: Logic separate from UI
- **Event-Driven**: Button clicks trigger calculations
- **Error Handling**: Graceful failure with user feedback

## Testing

Both implementations include comprehensive test suites with **40+ test cases** each.

### Python Testing
```bash
cd app
python3 test_calculator.py
```

### Java Testing
```bash
cd java-calculator
mvn test
```

### Code Coverage

**Java** (with JaCoCo):
```bash
cd java-calculator
mvn clean test jacoco:report
# View at: target/site/jacoco/index.html
```

**Python** (with pytest-cov):
```bash
cd app
pytest --cov=calculator --cov-report=html
# View at: htmlcov/index.html
```

### Test Categories

Both implementations test:
- ✅ Square root operations (positive, negative, zero, edge cases)
- ✅ Factorial operations (integers, negative, non-integer inputs)
- ✅ Natural logarithm (positive, zero, negative, large numbers)
- ✅ Power operations (positive/negative/fractional exponents)
- ✅ Basic arithmetic (add, subtract, multiply, divide)
- ✅ Error handling (division by zero, invalid inputs)

**Status**: All tests passing ✓

## Technology Stack

### Python Implementation
- **Language**: Python 3.6+
- **GUI**: Tkinter (standard library)
- **Math**: math module (standard library)
- **Testing**: unittest / pytest
- **Coverage**: pytest-cov

### Java Implementation
- **Language**: Java 11+
- **GUI**: Swing (javax.swing)
- **Build**: Maven 3.6+
- **Testing**: JUnit 5.9.3
- **Coverage**: JaCoCo 0.8.10

## Contributing

See implementation-specific READMEs:
- [Python Contributing Guide](app/README.md#contributing)
- [Java Development Guide](java-calculator/README.md)

## License

This project is part of an academic assignment for CSE 816 SPE.

## Author

**Nathan Mathew V**
- Repository: [ScientificCalculator-DevOps](https://github.com/nathanmathewv/ScientificCalculator-DevOps)

## Acknowledgments

Built using standard libraries and frameworks:
- Python: Tkinter, math, unittest
- Java: Swing, Maven, JUnit 5