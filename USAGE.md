# Calculator Usage Guide

## Quick Start

After building the project with `mvn package`, run:

```bash
java -jar target/scientific-calculator-1.0.0.jar
```

You'll see:

```
╔════════════════════════════════════════╗
║     Scientific Calculator v1.0         ║
║         Choose Your Mode               ║
╚════════════════════════════════════════╝

Select calculator mode:
  1. CLI Mode (Command Line Interface)
  2. GUI Mode (Graphical User Interface)

Enter your choice (1 or 2):
```

## CLI Mode Features

Select option `1` to enter CLI mode. You'll see an interactive menu:

```
┌─────────────────────────────────────┐
│         SELECT OPERATION            │
├─────────────────────────────────────┤
│  Basic Operations:                  │
│   1. Addition (+)                   │
│   2. Subtraction (-)                │
│   3. Multiplication (×)             │
│   4. Division (÷)                   │
│                                     │
│  Scientific Functions:              │
│   5. Square Root (√)                │
│   6. Power (x^y)                    │
│   7. Factorial (n!)                 │
│   8. Natural Logarithm (ln)         │
│   9. Common Logarithm (log)         │
│  10. Exponential (e^x)              │
│                                     │
│   0. Exit                           │
└─────────────────────────────────────┘
```

### CLI Example Session

```
Enter your choice: 5
Enter number: 16
✓ Result: √16.0 = 4.0

Enter your choice: 6
Enter base: 2
Enter exponent: 10
✓ Result: 2.0^10.0 = 1024.0

Enter your choice: 7
Enter non-negative integer: 5
✓ Result: 5! = 120

Enter your choice: 8
Enter positive number: 2.718281828
✓ Result: ln(2.718281828) = 0.9999999998311266

Enter your choice: 0
Thank you for using Scientific Calculator!
```

## GUI Mode Features

Select option `2` to launch the graphical interface.

### GUI Layout

```
┌────────────────────────────────────────┐
│  Scientific Calculator                 │
├────────────────────────────────────────┤
│                                     0  │ ← Display
├────────────────────────────────────────┤
│  √   │ x^y │ n!  │ ln  │            │
│  C   │ CE  │ ←   │ ÷   │            │
│  7   │ 8   │ 9   │ ×   │            │
│  4   │ 5   │ 6   │ -   │            │
│  1   │ 2   │ 3   │ +   │            │
│  ±   │ 0   │ .   │ =   │            │
└────────────────────────────────────────┘
```

### Button Color Coding

- **Yellow buttons**: Scientific functions (√, x^y, n!, ln)
- **Red buttons**: Clear operations (C, CE, ←)
- **Cyan/Teal buttons**: Arithmetic operations (+, -, ×, ÷, =)
- **Gray buttons**: Numbers and decimal point (0-9, ., ±)

### GUI Usage Examples

#### Example 1: Square Root
1. Enter `25`
2. Click `√`
3. Result: `5`

#### Example 2: Power
1. Enter `2`
2. Click `x^y`
3. Enter `8`
4. Click `=`
5. Result: `256`

#### Example 3: Factorial
1. Enter `6`
2. Click `n!`
3. Result: `720`

#### Example 4: Natural Logarithm
1. Enter `10`
2. Click `ln`
3. Result: `2.302585093`

## Error Handling

Both modes handle errors gracefully:

### CLI Mode Errors
```
Enter your choice: 5
Enter number: -4
✗ Error: Cannot calculate square root of negative number

Enter your choice: 7
Enter non-negative integer: -3
✗ Error: Factorial not defined for negative numbers

Enter your choice: 4
Enter numerator: 10
Enter denominator: 0
✗ Error: Cannot divide by zero
```

### GUI Mode Errors
- Displays error dialog boxes with clear messages
- Automatically resets calculator after error
- Prevents invalid operations

## Running Individual Modes

### CLI Only
```bash
java -cp target/classes com.calculator.CalculatorCLI
```

### GUI Only
```bash
java -cp target/classes com.calculator.CalculatorGUI
```

## Testing

Run comprehensive test suite (40+ test cases):

```bash
mvn test
```

Generate code coverage report:

```bash
mvn clean test jacoco:report
open target/site/jacoco/index.html
```

## Docker Usage

Build and run in Docker container:

```bash
# Build image
docker build -t scientific-calculator-java .

# Run container (defaults to interactive mode selector)
docker run -it scientific-calculator-java

# For GUI mode with X11 forwarding (macOS/Linux)
xhost +local:docker
docker run -it \
  -e DISPLAY=$DISPLAY \
  -v /tmp/.X11-unix:/tmp/.X11-unix \
  scientific-calculator-java
```

## Keyboard Shortcuts (GUI Mode)

- **Numbers (0-9)**: Type directly
- **Operators (+, -, *, /)**: Type directly
- **Enter**: Execute calculation (=)
- **Escape**: Clear all (C)
- **Backspace**: Delete last character (←)
- **Delete**: Clear entry (CE)

## Tips

1. **CLI Mode**: Perfect for server environments or when running in containers
2. **GUI Mode**: Best for desktop use with visual feedback
3. **Factorial**: Only works with non-negative integers (0-20 for reasonable results)
4. **Logarithms**: Only accept positive numbers
5. **Square Root**: Only accepts non-negative numbers
6. **Division**: Division by zero is caught and reported as an error

## Supported Operations

| Operation | Symbol | CLI Option | GUI Button | Example |
|-----------|--------|------------|------------|---------|
| Addition | + | 1 | + | 5 + 3 = 8 |
| Subtraction | - | 2 | - | 10 - 4 = 6 |
| Multiplication | × | 3 | × | 7 × 6 = 42 |
| Division | ÷ | 4 | ÷ | 20 ÷ 4 = 5 |
| Square Root | √ | 5 | √ | √25 = 5 |
| Power | x^y | 6 | x^y | 2^10 = 1024 |
| Factorial | n! | 7 | n! | 5! = 120 |
| Natural Log | ln | 8 | ln | ln(e) = 1 |
| Common Log | log₁₀ | 9 | - | log(100) = 2 |
| Exponential | e^x | 10 | - | e^1 = 2.718 |

## Build Information

- **Java Version**: 17
- **Build Tool**: Maven 3.9+
- **Test Framework**: JUnit 5.9.3
- **Code Coverage**: JaCoCo 0.8.10
- **Container**: Docker with OpenJDK 17
