# Scientific Calculator - Java Version

A scientific calculator built with Java Swing and Maven. Supports basic arithmetic operations and essential scientific functions.

## Features

### Basic Operations
- Addition, Subtraction, Multiplication, Division
- Decimal point support
- Sign change (positive/negative)
- Backspace and clear functions

### Scientific Functions (Core Features)
- Square Root (√): Calculate square root of a number
- Power (x^y): Raise a number to any power
- Factorial (n!): Calculate factorial of an integer
- Natural Logarithm (ln): Calculate natural logarithm (base e)

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

## Building the Project

### Compile the project:
```bash
mvn clean compile
```

### Run tests:
```bash
mvn test
```

### Generate code coverage report:
```bash
mvn clean test jacoco:report
```
Coverage report will be available at: `target/site/jacoco/index.html`

### Package as JAR:
```bash
mvn clean package
```

## Running the Calculator

### Option 1: Run with Maven
```bash
mvn exec:java -Dexec.mainClass="com.calculator.CalculatorGUI"
```

### Option 2: Run the JAR file
```bash
java -jar target/scientific-calculator-1.0.0.jar
```

### Option 3: Run with java command
```bash
mvn compile
java -cp target/classes com.calculator.CalculatorGUI
```

## Project Structure

```
java-calculator/
├── pom.xml                          # Maven configuration
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── calculator/
│   │               ├── Calculator.java      # Core calculator logic
│   │               └── CalculatorGUI.java   # Swing GUI
│   └── test/
│       └── java/
│           └── com/
│               └── calculator/
│                   └── CalculatorTest.java  # JUnit 5 tests
└── README.md
```

## Testing

The project includes comprehensive JUnit 5 tests covering:

### Square Root Tests
- Positive integers and decimals
- Zero
- Negative numbers (error cases)
- Large numbers

### Factorial Tests
- Small positive integers
- Zero
- Negative numbers (error cases)
- Larger values

### Natural Logarithm Tests
- Positive numbers
- Known values (ln(2), ln(10), etc.)
- Decimal numbers
- Zero and negative numbers (error cases)

### Power Function Tests
- Positive and negative exponents
- Zero exponent
- Fractional exponents (roots)
- Negative base
- Zero base
- Large exponents

### Basic Operations Tests
- Addition, subtraction, multiplication, division
- Division by zero (error case)

Run tests with:
```bash
mvn test
```

View test report:
```bash
mvn surefire-report:report
```

## Maven Commands Cheat Sheet

| Command | Description |
|---------|-------------|
| `mvn clean` | Clean build artifacts |
| `mvn compile` | Compile source code |
| `mvn test` | Run unit tests |
| `mvn package` | Create JAR file |
| `mvn clean install` | Full build and install |
| `mvn jacoco:report` | Generate code coverage report |
| `mvn surefire-report:report` | Generate test report |

## Dependencies

### Runtime
- Java Standard Library (Swing for GUI, Math for calculations)

### Testing
- JUnit Jupiter 5.9.3 (JUnit 5)
- JUnit Jupiter Params (for parameterized tests)

### Build Plugins
- Maven Compiler Plugin 3.11.0
- Maven Surefire Plugin 3.0.0 (test runner)
- Maven JAR Plugin 3.3.0
- JaCoCo Maven Plugin 0.8.10 (code coverage)

## How to Use

### Basic Calculations
Click number buttons and operation buttons (+, -, ×, ÷), then press = to calculate the result.

### Scientific Functions
- Square Root (√): Enter a number and click √
- Power (x^y): Enter base, click x^y, enter exponent, click =
- Factorial (n!): Enter an integer and click n!
- Natural Logarithm (ln): Enter a number and click ln

### Clear Functions
- C: Clear all (reset calculator)
- CE: Clear current entry
- ←: Backspace (remove last digit)

### Sign Change
- ±: Toggle between positive and negative

## Examples

Calculate √144:
- Enter 144, click √
- Result: 12

Calculate 2^8:
- Enter 2, click x^y, enter 8, click =
- Result: 256

Calculate 5!:
- Enter 5, click n!
- Result: 120

Calculate ln(10):
- Enter 10, click ln
- Result: 2.302585093

## Button Color Scheme
- Red: Clear/Delete operations (C, CE, ←)
- Cyan: Basic arithmetic operations (+, -, ×, ÷, =)
- Yellow: Scientific functions (√, x^y, n!, ln)
- Gray: Numbers and decimal point

## Error Handling

The calculator handles common error cases:
- Division by zero
- Square root of negative numbers
- Logarithm of non-positive numbers
- Factorial of negative or non-integer numbers

## Author

Nathan Mathew V

## License

This project is part of an academic assignment.
